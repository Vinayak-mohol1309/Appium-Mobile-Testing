package projects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@SuppressWarnings("deprecation")
public class InstagramAppiumComplexFunctionalitiesTest {
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

    public void performComplexFunctionalities() {
        // Example: Scroll to a specific post in the Instagram feed
        WebElement feed = driver.findElement(MobileBy.id("com.instagram.android:id/feed"));
        WebElement postToScrollTo = feed.findElement(MobileBy.xpath("//android.widget.ImageView[@content-desc='Desired Post']"));
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"com.instagram.android:id/feed\")).scrollIntoView("
                        + "new UiSelector().description(\"Desired Post\"));"
        ));

        // Example: Double tap on a photo to like it
        WebElement photo = driver.findElement(MobileBy.id("com.instagram.android:id/photo"));
     
        int tapCenterX = photo.getLocation().getX() + (photo.getSize().getWidth() / 2);
        int tapCenterY = photo.getLocation().getY() + (photo.getSize().getHeight() / 2);
        driver.tap(PointOption.point(tapCenterX, tapCenterY));

        // Example: Pinch out on an image to zoom in
        WebElement imageToZoom = driver.findElement(MobileBy.id("com.instagram.android:id/image"));
        int zoomCenterX = imageToZoom.getLocation().getX() + (imageToZoom.getSize().getWidth() / 2);
        int zoomCenterY = imageToZoom.getLocation().getY() + (imageToZoom.getSize().getHeight() / 2);
        int zoomRadius = (int) (imageToZoom.getSize().getHeight() * 0.4);
        driver.zoom(PointOption.point(zoomCenterX, zoomCenterY)).radius(zoomRadius).perform();
       
        
    }

    public void tearDown() {
        // Quit the driver
        if (driver != null) {
            driver.quit();
        }
    }
}
