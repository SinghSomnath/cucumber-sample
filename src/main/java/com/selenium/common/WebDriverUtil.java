package com.selenium.common;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base framework for the Web Automation .
 * @author somnath.singh...
 *
 */
public class WebDriverUtil {

	private static int PAGE_LOAD_TIMEOUT = 30;


	
	public static void waitForPageToBeLoad(WebDriver driver) {
		int pageLoadTimeOut=0;
		try {
			pageLoadTimeOut=120;

			if(null!="120"){
				int time = 0;
				while (time <= pageLoadTimeOut) {
					Thread.sleep(2000);
					if (!((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState")
							.equals("complete")) {
						time = time + 5;
					} else {
						break;
					}
				}

				System.out.println("Page loaded successfully in : "+time+5+" secs");
			}else{

			}

		} catch (Exception e) {
			
			System.out.println("waitForPageToBeLoad() : Unable to load page in given time in "+pageLoadTimeOut+" mins" + e);
			e.printStackTrace();

			
		}
	}





	public static boolean waitForElementToVisible(WebDriver driver,String xPath) {
		
	
		try{
			
			System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "(" + PAGE_LOAD_TIMEOUT + ") with xPath " +  xPath);
		
			WebDriverWait wait = new WebDriverWait(driver, PAGE_LOAD_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
		}

		catch(TimeoutException e)
		{
			return false;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * Waiting for the element to be visible...
	 * @param driver
	 * @param xPath
	 * @return
	 */
	public static boolean waitForElementVisible(WebDriver driver,String xPath,long timeOutInSecs) {
		
		System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "(" + timeOutInSecs + ") with xPath " +  xPath);
		
		try{
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSecs);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	public static boolean waitForElementToClickable(WebDriver driver,String xPath) {
		
		System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "(" + 60 + ") with xPath " +  xPath);
		
		try {			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));

		} catch (Exception e) {			
			return false;
		}
		return true;
	}

	public static boolean waitForElementToClickable(WebDriver driver,String xPath,long i) {
		
		System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "(" + i + ") with xPath " +  xPath);
		
		try {			
			WebDriverWait wait = new WebDriverWait(driver, i);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));

		} catch (Exception e) {			
			return false;
		}
		return true;
	}	


	public static WebElement getWebElement(WebDriver driver , String xPath)
	{
		System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "() with xPath " +  xPath);
		return driver.findElement(By.xpath(xPath));
	}

	public static List<WebElement> getWebElements(WebDriver driver , String xPath)
	{
		System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "() with xPath " +  xPath);
		return driver.findElements(By.xpath(xPath));
	}

	public static String getAttributeValue(WebDriver driver , String xPath,String attribute)
	{
		System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "() with xPath " +  xPath);
		return getWebElement(driver, xPath).getAttribute(attribute);
	}
	public static void click(WebDriver driver,String xPath)	
	{
		waitForElementToClickable(driver, xPath);
		WebElement el = getWebElement(driver, xPath);		
		System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "() with xPath " +  xPath);
		el.click();


	}
	public static void actionClick(WebDriver driver,String xPath)	
	{
		
		WebElement element = getWebElement(driver, xPath);
		System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "() with xPath " +  xPath);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click();
		Action action = builder.build();
		action.perform();

	}
	public static boolean isDisplayed(WebDriver driver,String xPath){

		
		try{
			WebElement el = getWebElement(driver, xPath);
			System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "() with xPath " +  xPath);
			el.isDisplayed();
			
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
		return true;
	}
	public static boolean isEnabled(WebDriver driver,String xPath){

		
		try{ 
			WebElement  el = getWebElement(driver, xPath);
			System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "() with xPath " +  xPath);
			el.isEnabled();
		}
		catch(Exception e)
		{
			return false;
		}
		return true; 
	}
	public static String getText(WebDriver driver,String xPath)
	
	{
//		waitForElementVisible(driver, xPath,PAGE_LOAD_TIMEOUT );
		System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "() with xPath " +  xPath);
		WebElement el = getWebElement(driver, xPath);
		return el.getText();

	}

	public static int getXpathCount(WebDriver driver,String xPath)
	{
		System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "() with xPath " +  xPath);
		return getWebElements(driver, xPath).size();
	}

	public static String getMethodName()
	{
		StackTraceElement[] element = Thread.currentThread().getStackTrace();
		int cnt = 0;
		String s = element[2].toString();	
		s = s.substring(0,s.lastIndexOf("("));
		s = s.substring((s.lastIndexOf(".")+1),s.length());	     
		return s;
	}
	
	public static void get(WebDriver driver,String URL)
	{
		System.out.println("%%%%%%%%%%%%%%AAAUTOMAAATION LOGGG%%%%%%%%%% executing " + getMethodName() + "() with url " +  URL);
		driver.get(URL);
		waitForPageToBeLoad(driver);
	}
}
