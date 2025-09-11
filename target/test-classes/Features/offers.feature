Feature: Accessing offers
Scenario Outline: Accessing the facilities available in offers

Given the user clicks the offer icon
When  the user selects the dropdown More option
And selects tripType trip type
And  selects from as "<from>" 
And gives the location where as "<where>"
Then user selects the things to do option
