Feature: Explore Offers Section

  As a traveler
  I want to access and explore the offers section
  So that I can view available deals and activities

  @Offers
  Scenario Outline: Access facilities available in the Offers section

Given the user clicks the offer icon
When  the user selects the dropdown More option
And selects tripType trip type
And  selects from as "<from>" 
And gives the location where as "<where>"
Then user selects the things to do option