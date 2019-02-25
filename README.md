# cucumber-sample
Demo cucumber framework with Discovery web automation
Works with Chrome browser 70.0.+ with Chromedriver 2.43.+

Project Details :
------------------
Base Language - Java  
Base Framework - TestNG  
BDD Framework - Cucumber  
Build Tool - Maven .  

The steps are written with BDD format in "discovery.feature" file inside "features" folder .
The step defintion class is "DiscoveryStepDefinition.java" which is inside "com.cucumber.testdefinitions" package with in "cucumber-sample\src\test\java" folder path  .
The test runner class is "TestRunner.java" which is inside com.test.runner "package" with in "cucumber-sample\src\test\java" folder path .
The locators are inside "com.discovery.locators" package which is following POM (Page Object Model ) .
The Selenium wrapper Utililty project is inside "WebDriverUtil.java".
The Discovery related project web Utility is inside "DiscoveryWebUtil.java"


To run the test from command line :
------------------------------------
In the command prompt 
Go inside "cucumber-sample" folder and run "mvn clean compile test" command .
The "test" phase will invoke SureFire plugin inside "pom.xml" > which will run "testng.xml" > The "testng.xml" will run the runner class which is "TestRunner.java" which is inside com.test.runner "package" with in "cucumber-sample\src\test\java" folder path .

To Setup the project in Eclipse :
-----------------------------------
1. Install EGit plugin in Eclipse .
2. Import the project by right clicking "import" inside eclipse > Select "Git" - "Projects From Git" - select "Clone URI" -  give uri as https://github.com/SinghSomnath/cucumber-sample.git filling your git credentials in the form . 
