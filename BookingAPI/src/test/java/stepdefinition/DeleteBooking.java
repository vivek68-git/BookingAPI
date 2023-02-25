package stepdefinition;


import io.cucumber.java.en.When;
import testcontext.TestContext;


public class DeleteBooking {
	private TestContext context = TestContext.getContext();

	@When("user makes a request to delete booking with basic auth {string} & {string}")
	public void userMakesARequestToDeleteBookingWithBasicAuth(String username, String password) {
		context.response = context.requestSetup()
				.auth().preemptive().basic(username, password)
				.pathParam("bookingID", context.session.get("bookingID"))
				.when().delete(context.session.get("endpoint")+"/{bookingID}");
	}
}
