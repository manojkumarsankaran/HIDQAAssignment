@APIAutomation
Feature: API Automation Testing

@API @Test
Scenario Outline: Get request functionality

Given user sends GET request
Then verify the count of "<categories>"
Then verify the name of "<category1>" and "<category2>"

Examples:
|categories                                       |category1|category2          |
|atm,cafe,shopping,food,lodging,attraction,default|food     |geolocation_degrees|



