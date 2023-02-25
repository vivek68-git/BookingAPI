@bookingAPI @partialBooking
Feature: To do partial update of booking in restful-booker

  Background: create an auth token
    Given user has access to endpoint "/auth"
    When user creates a auth token with credential "admin" & "password123"
    Then user should get the response code 200

  @partialUpdateBooking
  Scenario: To partially update a booking
    Given user has access to endpoint "/booking"
    When user makes a request to view booking IDs
    And user makes a request to update first name "Chris" & Last name "Green"
    Then user should get the response code 200
    And user validates the response with JSON schema "bookingDetailsSchema.json"
