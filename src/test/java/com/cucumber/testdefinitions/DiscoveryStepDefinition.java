package com.cucumber.testdefinitions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.discovery.locators.DiscoveryHomePage_Locators;
import com.discovery.locators.DiscoveryMyVideos_Page_Locators;
import com.selenium.common.WebDriverUtil;
import com.selenium.discovery.util.DiscoveryWebUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DiscoveryStepDefinition {

	WebDriver driver;

	Map<String,String> mapVideoDetailsExpected = new HashMap<String,String>();

	@Given("^Navigate to Discovery.com$")
	public void navigate_to_Discovery(){

		String driverPath = "/drivers";

		driverPath = System.getProperty("user.dir") + driverPath + "/chromedriver.exe";

		System.setProperty("webdriver.chrome.driver",
				driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverUtil.get(driver, "https://www.Discovery.com");

	}


	@When("^Select 2 videos from recommended for you and favorite them$")
	public void addVideosFav() throws Exception{


		//Scrolling till Recommended Video .
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", WebDriverUtil.getWebElement(driver, DiscoveryHomePage_Locators.divRecommendedVideoTag));

		String sRecomVideoLoctr = DiscoveryHomePage_Locators.divRecommendedSingleVideo.replaceAll("num", "1");
		String sRecomVideoLoctr2 = DiscoveryHomePage_Locators.divRecommendedSingleVideo.replaceAll("num", "2");

		//Adding the first video as favourite in the recommeneded section storing the d	
		mapVideoDetailsExpected.putAll(DiscoveryWebUtil.addVideoToFav(driver, sRecomVideoLoctr));
		mapVideoDetailsExpected.putAll(DiscoveryWebUtil.addVideoToFav(driver, sRecomVideoLoctr2));



	}


	@When("^Go to my videos \"(.*)\"$")
	public void goToMyVideo(String link) throws Exception{

		WebDriverUtil.get(driver, link.trim());

	}

	@Then("^Assert that the videos appear with the correct show title and description$")
	public void verifyFavVideos() throws Exception{

		Map<String,String> mapFavVideosDetails = new HashMap<String,String>();

		String sKeyActual, sKeyExpected, sValActual, sValExpected;

		//Scrolling till the Fav Video .
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", WebDriverUtil.getWebElement(driver, DiscoveryMyVideos_Page_Locators.headerFavShows));

		String sRecomVideoLoctr = DiscoveryHomePage_Locators.divRecommendedSingleVideo.replaceAll("num", "1");
		String sRecomVideoLoctr2 = DiscoveryHomePage_Locators.divRecommendedSingleVideo.replaceAll("num", "2");

		//Adding the first video as favourite in the recommeneded section storing the d	
		mapFavVideosDetails.putAll(DiscoveryWebUtil.fetchFavVideoInfo(driver, sRecomVideoLoctr));
		mapFavVideosDetails.putAll(DiscoveryWebUtil.fetchFavVideoInfo(driver, sRecomVideoLoctr2));



		Set<String> setActual_Title = mapFavVideosDetails.keySet();
		Set<String> setExpected_Title = mapVideoDetailsExpected.keySet();

		if(setActual_Title.size() == setExpected_Title.size())
		{

			for (Iterator iterator = setExpected_Title.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();


				Assert.assertTrue(setActual_Title.contains(string), " The video titled with " + string + " is not added successfully as the favourite video");
				Assert.assertEquals(mapFavVideosDetails.get(string), mapVideoDetailsExpected.get(string)," Description is not matching For The video titled with " + string + " which was added as the favourite video");

			}

		}




	}





}
