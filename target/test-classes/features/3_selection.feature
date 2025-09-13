Feature: Flight Search Filtering

  As a traveler
  I want to filter flights by departure and arrival times
  So that I can quickly find flights that match my preferences

 @FlightFilters
Scenario Outline: Filter flights by departure and arrival times
  
Given the user is on the selection page
And the user clicks first filter departure as "<Departure>"
And the user clicks second filter arrival as "<Arrival>"
Then select the first available flight
 

Examples:
|Departure|Arrival|
|Before 6 AM|12 PM - 6 PM|