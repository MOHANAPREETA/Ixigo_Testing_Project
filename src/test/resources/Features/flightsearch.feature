Feature: flight searching successfully

Scenario: Successful flight search
Given the user is on the login page
And the user selects the from as "<from>" and to as "<to>"
And the user selects the departure and return
And the user clicks the travellars icon
And the user selects the special fare 
Then the user clicks the search button

Examples:
|from|to|
|Chennai|New Delhi|

