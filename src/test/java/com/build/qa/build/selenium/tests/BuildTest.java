package com.build.qa.build.selenium.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.build.qa.build.selenium.framework.BaseFramework;

import com.build.qa.build.selenium.pageobjects.homepage.HomePage;

public class BuildTest extends BaseFramework { 
	
	/** 
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test
	public void navigateToHomePage() { 
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		
		softly.assertThat(homePage.onBuildTheme())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}
	
	/** 
	 * Search for the Quoizel MY1613 from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() { 
		
		driver.get("https://www.build.com"); 
		WebElement searchBox=driver.findElement(By.xpath("//*[@id=\"search_txt\"]"));
		System.out.print(searchBox.getLocation());
		searchBox.sendKeys("Quoizel");
		
		driver.findElement(By.cssSelector(".search-site-search")).click();
		String ResultVal= driver.findElement(By.cssSelector(".page-heading")).getText();
		System.out.println("result:"+ResultVal);
		assertEquals("Quoizel",ResultVal);
		
		
	}
	
	/** 
	 * Go to the Bathroom Sinks category directly (https://www.build.com/bathroom-sinks/c108504) 
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() { 
		// TODO: Implement this test
		driver.get("https://www.build.com/bathroom-sinks/c108504");
		WebElement category=driver.findElement(By.cssSelector("#product-composite-560600 > div:nth-child(2) > a:nth-child(1) > div:nth-child(1) > img:nth-child(1)"));
		String expectedValue=category.getText();
		category.click();
		driver.findElement(By.cssSelector("#add-to-cart-wrap > button")).click();
		String actualValue= driver.findElement(By.cssSelector(".col-xs-9 > a:nth-child(1) > p:nth-child(1)")).getText();
		assertTrue("Your error message", actualValue.contains(expectedValue));
	}
	
	/** 
	 * Add a product to the cart and email the cart to yourself, also to my email address: jgilmore+SeleniumTest@build.com
	 * Include this message in the "message field" of the email form: "This is {yourName}, sending you a cart from my automation!"
	 * @assert that the "Cart Sent" success message is displayed after emailing the cart
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addProductToCartAndEmailIt() { 
		// TODO: Implement this test
		
		driver.get("https://www.build.com"); 
		WebElement searchBox=driver.findElement(By.xpath("//*[@id=\"search_txt\"]"));
		System.out.print(searchBox.getLocation());
		searchBox.sendKeys("Quoizel");
		
		driver.findElement(By.cssSelector(".search-site-search")).click();
		
		driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/section[3]/section[1]/div/div[6]/div/a/div/span")).click();
		driver.findElement(By.cssSelector("#product-composite-654158 > div.product-tile > a > div.product-tile-image.margin-top-sm > img")).click();
		driver.findElement(By.cssSelector("#add-to-cart-wrap > button")).click();	
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a")).click();
		driver.findElement(By.cssSelector(".btn-email")).click();
		driver.findElement(By.cssSelector("#yourName")).sendKeys("TestUser");
		driver.findElement(By.cssSelector("#yourEmail")).sendKeys("mukund82@gmail.com");
		driver.findElement(By.cssSelector("#recipientName")).sendKeys("build.comtester");
		driver.findElement(By.cssSelector("#recipientEmail")).sendKeys("jgilmore+SeleniumTest@build.com");
		driver.findElement(By.cssSelector("#quoteMessage")).sendKeys("This is Mukund, sending you a cart from my automation!");
		     
		String expectedString="Cart Sent! The cart has been submitted to the recipient via email.";
		String actualText=driver.findElement(By.cssSelector("#header > div.container-fluid > div ")).getText();
		assertTrue("Your error message", actualText.contains(expectedString));
	}
	
	/** 
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() { 
		// TODO: Implement this test
	}
}
