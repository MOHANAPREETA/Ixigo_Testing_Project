Feature: FlightBooking


@PostiveLogin
Scenario: Successful login with valid details 
Given the is on the login page
When the user enters mobileno as "<Mobile_no>"
And enters the correct OTP
Then user should be navigated to the booking page

@NegativeLogin
Scenario: Unsuccessful login with invalid deatils
Given the user in on the login page
When the user attemts to enter inavlid mobileno as "<Mobile_No>"
And the user clicks the login button
Then the system should display "<error_message>"

@InvalidUrl
Scenario: Entering an invalid Url
Given the user enters "https://www.ixgo.com/"
When the page is loaded
Then the website should show error "Page not found"
