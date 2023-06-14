package projects;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppimCalculator {
//as we start , Appium server should on running and device should be connected.
	public static void main(String[] args) throws MalformedURLException {
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "write android version");
		dc.setCapability(MobileCapabilityType.APP, "apk file path");
		// in case of Native app we must have to add two more capabilities
		dc.setCapability("appPackage","appPackage value");//APP INFO->calculator
		dc.setCapability("appActivity","appActivity value");//APP INFO->select calculator->activities
		
		URL url = new URL("http://localhost:4723/wd/hub");
		
		AppiumDriver driver = new AppiumDriver(url,dc);//it will open app 
		
		WebElement seven = driver.findElement(By.id(""));// seven / 7 number
		//while finding elements , it must have unique attribute value 
		seven.click();
		driver.findElement(By.id("")).click();// +
		WebElement three = driver.findElement(By.id(""));// three / 3 number
		three.click();
		driver.findElement(By.id("")).click();// =
		driver.findElement(By.id(""));
		String result = driver.findElement(By.id("")).getText(); 
		
		if(result.equals("10")) {
			System.out.println("passed");
		}
		else {
			System.out.println("failed");
		}
		
	}

}
