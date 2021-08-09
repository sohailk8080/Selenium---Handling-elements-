package org.mvnframework.mvnFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PracticePage {
	WebDriver driver;
	String baseURL;

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		baseURL = "https://courses.letskodeit.com/practice";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
	}

	@Test
	public void practiceElements() throws InterruptedException {

		// radio button name
		WebElement element = driver
				.findElement(By.xpath("//input[@id=\"bmwradio\"]//parent::label//preceding-sibling::legend"));
		String s = element.getText();
		System.out.println("element name= " + s);

		// get all options name in radio button
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='radio-btn-example']//label"));
		int size = list.size();
		System.out.println("Size of list is::" + size);
		for (int i = 0; i < size; i++) {
			System.out.println("List of radio buttons include: " + list.get(i).getText());
		}

		// To click radiobuttons
		Boolean btnClick = false;
		List<WebElement> radioBtnClick = driver.findElements(By.xpath("//input[contains(@type,'radio')]"));
		int radioBtnSize = radioBtnClick.size();
		for (int i = 0; i < radioBtnSize; i++) {

			btnClick = radioBtnClick.get(i).isSelected();
			if (!btnClick) {
				radioBtnClick.get(i).click();
				Thread.sleep(3000);

			}
		}
		// select example name
		WebElement selElement = driver.findElement(By.xpath("//select[@id='carselect']//preceding-sibling::legend"));
		String selName = selElement.getText();
		System.out.println("element name= " + selName);

		// Select class example
		List<WebElement> selectClass = driver.findElements(By.xpath("//select[@id='carselect']"));
		for (WebElement we : selectClass) {
			String selVar = we.getText();
			System.out.println("List of select class::" + selVar);
		}

		// selecting elements Using select class
		WebElement element1 = driver.findElement(By.xpath("//select[@id='carselect']"));
		Select sel = new Select(element1);
		sel.selectByValue("honda");
		Thread.sleep(2000);
		sel.selectByIndex(2);
		Thread.sleep(2000);
		sel.selectByVisibleText("BMW");
		Thread.sleep(2000);

		// Multi select example
		WebElement multiSel = driver.findElement(By.id("multiple-select-example"));
		System.out.println(multiSel.getText());

		// Selecting/Deselecting using select class
		Select seldesel = new Select(multiSel);
		seldesel.selectByIndex(0);
		Thread.sleep(2000);
		seldesel.deselectByIndex(0);
		Thread.sleep(2000);
		seldesel.selectByValue("orange");
		Thread.sleep(2000);
		seldesel.deselectByValue("orange");
		Thread.sleep(2000);
		seldesel.selectByVisibleText("Peach");
		Thread.sleep(2000);
		seldesel.deselectByVisibleText("Peach");

		// CheckBox List name
		Boolean boxCLick = false;
		List<WebElement> checkbox = driver
				.findElements(By.xpath("//input[contains(@type, 'checkbox') and (@name='cars')]"));
		for (WebElement boxList : checkbox) {
			String cbList = boxList.getText();
			System.out.println(cbList);

			// Selecting and deselecting checkboxes 
			if (!boxCLick) { boxList.click();
			Thread.sleep(2000);
		}

		// Switching window

		WebElement openWindow = driver.findElement(By.id("openwindow"));
		openWindow.click();

		// Parent window
		String parentHan = driver.getWindowHandle();

		// get all handles
		Set<String> windows = driver.getWindowHandles();

		// get target window
		for (String focusWin : windows) {
			if (!focusWin.equals(parentHan)) {
				driver.switchTo().window(focusWin);
				WebElement switch1 = driver.findElement(By.xpath("//a[@href='/login']"));
				switch1.click();
				driver.close();
				Thread.sleep(2000);
				driver.switchTo().window(parentHan);

			}
		}

		// switching tabs
		driver.findElement(By.id("opentab")).click();
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		tabs.remove(parentHan);
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		driver.close();
		driver.switchTo().window(parentHan);

		// Alert button example
		WebElement altTxt = driver.findElement(By.xpath("//input[@placeholder=\"Enter Your Name\"]"));
		altTxt.sendKeys("SOhail");
		WebElement altbtn = driver.findElement(By.id("alertbtn"));
		altbtn.click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
		WebElement cfmbtn = driver.findElement(By.id("confirmbtn"));
		cfmbtn.click();
		Alert alert1 = driver.switchTo().alert();
		Thread.sleep(2000);
		alert1.dismiss();
		
		//hide/show button
				Boolean visible=false;
				
				if(!visible) {
					
					driver.findElement(By.id("show-textbox")).click();
					WebElement showText=driver.findElement(By.id("displayed-text"));
					showText.sendKeys("types when txtfield visible");
					visible=showText.isEnabled();
					System.out.println(visible);
				}
				else {
					driver.findElement(By.id("hide-textbox")).click();
				}
	}
		}

	@AfterClass
	public void afterClass() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	}

}
