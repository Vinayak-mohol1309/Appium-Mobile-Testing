package projects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class InstagramAppiumTest {
    private AndroidDriver driver;

    public void setup() throws MalformedURLException {
        // Set desired capabilities for the Android device
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Android Device");
        caps.setCapability("udid", "your_device_udid");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "your_device_android_version");
        caps.setCapability("appPackage", "com.instagram.android");
        caps.setCapability("appActivity", "com.instagram.mainactivity.MainActivity");

        // Initialize the Appium driver
        URL serverUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(serverUrl, caps);
    }

    public void performAppiumMobileTesting() {
        // Explore Tab - Open first post
        WebElement exploreTab = driver.findElement(MobileBy.AccessibilityId("Explore"));
        exploreTab.click();
        WebElement firstPost = driver.findElement(MobileBy.id("com.instagram.android:id/image_button"));
        firstPost.click();

        // Like and comment on the post
        WebElement likeButton = driver.findElement(MobileBy.id("com.instagram.android:id/like_button"));
        likeButton.click();
        WebElement commentInput = driver.findElement(MobileBy.id("com.instagram.android:id/comment_input"));
        commentInput.sendKeys("Great post!");
        WebElement postButton = driver.findElement(MobileBy.id("com.instagram.android:id/post_button"));
        postButton.click();

        // Search for a user and follow them
        WebElement searchTab = driver.findElement(MobileBy.AccessibilityId("Search"));
        searchTab.click();
        WebElement searchInput = driver.findElement(MobileBy.id("com.instagram.android:id/action_bar_search_edit_text"));
        searchInput.sendKeys("username");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
   /*
    * we import the AndroidKey and KeyEvent classes from the io.appium.java_client.android.nativekey package.
    *  We then use the driver.pressKey() method with a new KeyEvent object constructed using AndroidKey.ENTER to 
    *  simulate pressing the Enter key.
    */
        
        WebElement followButton = driver.findElement(MobileBy.id("com.instagram.android:id/follow_button"));
        followButton.click();

        // View and like user's story
        WebElement storyAvatar = driver.findElement(MobileBy.id("com.instagram.android:id/story_ring"));
        storyAvatar.click();
        WebElement storyLikeButton = driver.findElement(MobileBy.id("com.instagram.android:id/like_button"));
        storyLikeButton.click();

        // Logout from Instagram
        WebElement profileTab = driver.findElement(MobileBy.AccessibilityId("Profile"));
        profileTab.click();
        WebElement menuButton = driver.findElement(MobileBy.id("com.instagram.android:id/menu_button"));
        menuButton.click();
        WebElement logoutButton = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Log Out']"));
        logoutButton.click();
        WebElement confirmLogoutButton = driver.findElement(MobileBy.id("android:id/button1"));
        confirmLogoutButton.click();
    }

    public void tearDown() {
        // Quit the driver
        if (driver != null) {
            driver.quit();
        }
    }
    
}

