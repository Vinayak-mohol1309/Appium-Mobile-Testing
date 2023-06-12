package projects;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class InstagramLoginRegistrationTest {
	public  AppiumDriver driver;

   // @BeforeTest
    public void setup() throws MalformedURLException {
        // Set desired capabilities for the Android device
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "your_device_android_version");
//        caps.setCapability("appPackage", "com.instagram.android");
//        caps.setCapability("appActivity", "com.instagram.mainactivity.MainActivity");
        caps.setCapability(MobileCapabilityType.APP,"apk file path");

        // Initialize the Appium driver
        URL serverUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(serverUrl, caps);
    }

  //  @Test
    public void testInstagramLoginRegistration() {
        // Check if the user already has an account
        boolean userHasAccount = checkUserHasAccount();

        if (userHasAccount) {
            // User has an account, perform login
            performLogin();
        } else {
            // User doesn't have an account, perform registration
            performRegistration();
        }

        // Example: Add additional test steps, interactions, and assertions as needed
    }

    private boolean checkUserHasAccount() {
        // Your logic to check if the user has an account or not
        // Return true if user has an account, false otherwise
        // Example: You can check if the login screen elements are present
       WebElement loginButton = driver.findElement(By.id("com.instagram.android:id/log_in_button"));
        return loginButton.isDisplayed();
    }

    private void performLogin() {
        // Your logic to perform login
        // Example: fill in username and password and click login button
        WebElement usernameInput = driver.findElement(By.id("com.instagram.android:id/login_username"));
        usernameInput.sendKeys("your_username");
        WebElement passwordInput = driver.findElement(By.id("com.instagram.android:id/password"));
        passwordInput.sendKeys("your_password");
        WebElement loginButton = driver.findElement(By.id("com.instagram.android:id/next_button"));
        loginButton.click();
    }

    private void performRegistration() {
        // Your logic to perform registration
        // Example: navigate to the registration screen and fill in registration details
        WebElement signUpButton = driver.findElement(By.id("com.instagram.android:id/log_in_button"));
        signUpButton.click();
        WebElement signUpWithEmail = driver.findElement(By.xpath("//android.widget.TextView[@text='Sign up with email or phone number']"));
        signUpWithEmail.click();

        // Fill in registration details
        WebElement emailInput = driver.findElement(By.id("com.instagram.android:id/email"));
        emailInput.sendKeys("your_email@example.com");
        WebElement fullNameInput = driver.findElement(By.id("com.instagram.android:id/full_name"));
        fullNameInput.sendKeys("Your Full Name");
        WebElement usernameInput = driver.findElement(By.id("com.instagram.android:id/username"));
        usernameInput.sendKeys("your_username");
        WebElement passwordInput = driver.findElement(By.id("com.instagram.android:id/password"));
        passwordInput.sendKeys("your_password");
        WebElement signUpButtonFinal = driver.findElement(By.id("com.instagram.android:id/sign_up_button"));
        signUpButtonFinal.click();
    }

   // @AfterTest
    public void tearDown() {
        // Quit the driver
        if (driver != null) {
            driver.quit();
        }
    }
    public static void main(String args[]) throws MalformedURLException {
    	InstagramLoginRegistrationTest obj1 = new InstagramLoginRegistrationTest();
    	obj1.setup();
    	obj1.testInstagramLoginRegistration();
    	obj1.tearDown();
		
	}
}

