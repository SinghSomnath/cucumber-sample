Feature: Testing Discovery

Scenario Outline: Testing Discovery Favourite Video


Given Navigate to Discovery.com
When Select 2 videos from recommended for you and favorite them  
When Go to my videos "<link>"
Then Assert that the videos appear with the correct show title and description

Examples:

	| link |
	| https://www.discovery.com/my-videos  |