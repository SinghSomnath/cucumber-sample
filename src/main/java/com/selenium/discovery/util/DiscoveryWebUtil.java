package com.selenium.discovery.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.selenium.common.WebDriverUtil;

public class DiscoveryWebUtil {
/**
 * Adds the video as Favourite
 * @param driver
 * @param sXpathVideo - Xpath of the video to be added 
 * @return Map<String,String> - title-description details of the favourite video .
 * @throws Exception
 */
	public static Map<String,String> addVideoToFav(WebDriver driver,String sXpathVideo) throws Exception
	{
		Map<String,String> mapVideoDetails = new HashMap<String,String>();
		
		String sLinkAdd = sXpathVideo + "/descendant::i[contains(@class,'icon-plus')]";
		String sTitle = sXpathVideo + "/descendant::h3[contains(@class,'title')]/div";
		String sDescription = sXpathVideo + "/descendant::div[contains(@class,'description')]/div";
		
		WebElement el = WebDriverUtil.getWebElement(driver, sXpathVideo);
		
		sTitle = WebDriverUtil.getAttributeValue(driver, sTitle, "innerHTML");		
		sDescription = WebDriverUtil.getAttributeValue(driver, sDescription,  "innerHTML");
		

		//Storing the video details in a map as 'title' as a key and 'description' as a value
		mapVideoDetails.put(sTitle, sDescription);
		
		int elHeight = el.getSize().height;
		int elWidth = el.getSize().width;


		int xOffsetMid =  elWidth/2;
		int yOffsetMid = elHeight/2;

		//Hovering over the center of the video
		new Actions(driver).moveToElement(el, xOffsetMid, yOffsetMid).build().perform();

		//Waiting for + icon to appear and clicking it to add ii to the favourite video .
		WebDriverUtil.click(driver, sLinkAdd);
		
		return mapVideoDetails;


	}
	
	/**
	 * Fetching the favourite video details
	 * @param driver
	 * @param sXpathVideo - XPath of the favourite video
	 * @return Map<String,String> title-description details of the favourite video .
	 * @throws Exception
	 */
	public static Map<String,String> fetchFavVideoInfo(WebDriver driver,String sXpathVideo) throws Exception
	{
		Map<String,String> mapVideoDetails2 = new HashMap<String,String>();
		
		
		String sTitle = sXpathVideo + "/descendant::h3[contains(@class,'title')]/div";
		String sDescription = sXpathVideo + "/descendant::div[contains(@class,'description')]/div";
		
		WebElement el = WebDriverUtil.getWebElement(driver, sXpathVideo);
		
		sTitle = WebDriverUtil.getAttributeValue(driver, sTitle, "innerHTML");		
		sDescription = WebDriverUtil.getAttributeValue(driver, sDescription,  "innerHTML");
		

		//From Favourite Video section Storing the video details in a map as 'title' as a key and 'description' as a value
		mapVideoDetails2.put(sTitle, sDescription);
		
		
		
		return mapVideoDetails2;


	}

}
