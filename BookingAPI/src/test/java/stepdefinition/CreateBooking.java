package stepdefinition;

import static org.junit.Assert.*;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import helpers.ResponseHandler;
import helpers.JsonReaderUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import model.Booking;
import testcontext.TestContext;


public class CreateBooking{
	private TestContext context = TestContext.getContext();
	private static final Logger LOG = LogManager.getLogger(CreateBooking.class);

	@When("user creates a booking")
	public void userCreatesABooking(DataTable dataTable) {
		Map<String,String> bookingData = dataTable.asMaps().get(0);
		JSONObject bookingBody = new JSONObject();

		bookingBody.put("firstname", bookingData.get("firstname"));
		bookingBody.put("lastname", bookingData.get("lastname"));
		bookingBody.put("totalprice", Integer.valueOf(bookingData.get("totalprice")));
		bookingBody.put("depositpaid", Boolean.valueOf(bookingData.get("depositpaid")));

		JSONObject bookingDates = new JSONObject();

		bookingDates.put("checkin", (bookingData.get("checkin")));
		bookingDates.put("checkout", (bookingData.get("checkout")));
		bookingBody.put("bookingdates", bookingDates);
		bookingBody.put("additionalneeds", bookingData.get("additionalneeds"));

		context.response = context.requestSetup().body(bookingBody.toString())
				.when().post(context.session.get("endpoint").toString());

		Booking booking = ResponseHandler.deserializedResponse(context.response, Booking.class);
		assertNotNull("Booking not created", booking);
		LOG.info("Newly created booking ID: "+ booking.getBookingid());
		context.session.put("bookingID", booking.getBookingid());
		validateBookingData(new JSONObject(bookingData), booking);
	}

	private void validateBookingData(JSONObject bookingData, Booking booking) {
		LOG.info(bookingData);
		assertNotNull("Booking ID missing", booking.getBookingid());
		assertEquals("First Name did not match", bookingData.get("firstname"), booking.getBooking().getFirstname());
		assertEquals("Last Name did not match", bookingData.get("lastname"), booking.getBooking().getLastname());
		assertEquals("Total Price did not match", bookingData.get("totalprice"), booking.getBooking().getTotalprice());
		assertEquals("Deposit Paid did not match", bookingData.get("depositpaid"), booking.getBooking().getDepositpaid());
		assertEquals("Additional Needs did not match", bookingData.get("additionalneeds"), booking.getBooking().getAdditionalneeds());
		assertEquals("Check in Date did not match", bookingData.get("checkin"), booking.getBooking().getBookingdates().getCheckin());
		assertEquals("Check out Date did not match", bookingData.get("checkout"), booking.getBooking().getBookingdates().getCheckout());
	}

	@When("user creates a booking using data {string} from JSON file {string}")
	public void userCreatesABookingUsingDataFromJSONFile(String dataKey, String JSONFile) {
		context.response = context.requestSetup().body(JsonReaderUtil.getRequestBody(JSONFile,dataKey))
				.when().post(context.session.get("endpoint").toString());

		Booking booking = ResponseHandler.deserializedResponse(context.response, Booking.class);
		assertNotNull("Booking not created", booking);
		LOG.info("Newly created booking ID: "+booking.getBookingid());	
		context.session.put("bookingID", booking.getBookingid());
	}
}