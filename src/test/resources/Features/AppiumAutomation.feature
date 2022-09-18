@AppiumAutomation
Feature: Appium Automation Testing

@Appium @Test
Scenario: Test the play store functions

Given appium server is started running
When user opens the apps in play store application
And user navigates to categories tab
Then verify Travel & Local is not displayed in games tab
And verify Travel & Local is displayed is apps tab
And verify indigo flight app is displayed in Travel & Local




