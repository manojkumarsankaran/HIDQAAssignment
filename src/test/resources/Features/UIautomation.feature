@Automation
Feature: UI Automation Testing

@selenium1 @Test
Scenario: Create a file with Main concepts sub elements

Given user launches the react jS URL
When user is navigated to Docs page
And user expands the Main concepts
Then Main concepts sub elements text should be saved in file


@Selenium2 @Test
Scenario: Create a file with Advanced Guides sub elements

Given user launches the react jS URL
When user is navigated to Docs page
And user expands the Advanced Guides
Then Advanced Guides sub elements text should be saved in file

@Selenium3 @Test
Scenario: Verify the scrolling functionality in tutorials tab

Given user launches the react jS URL
When user is navigated to tutorials page
And user scrolls down the page
Then verify respected content is bolded and blue color line is displayed



