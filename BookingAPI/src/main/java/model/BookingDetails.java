package model;

import lombok.Data;

@Data
public class BookingDetails {

	private String firstname;

	private String additionalneeds;

	private BookingDates bookingdates;

	private String totalprice;

	private String depositpaid;

	private String lastname;

}