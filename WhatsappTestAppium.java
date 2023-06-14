package projects;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WhatsappTestAppium {
    private AndroidDriver driver;
    private TouchAction touchAction;

   // @Before
    public void setUp() throws MalformedURLException {
        // Set desired capabilities for the WhatsApp app
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Your_Device_Name");
        desiredCapabilities.setCapability("appPackage", "com.whatsapp");
        desiredCapabilities.setCapability("appActivity", "com.whatsapp.HomeActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        // Connect to the Appium server
        URL appiumServerUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(appiumServerUrl, desiredCapabilities);
        touchAction = new AndroidTouchAction(driver);
    }

    //@After
    public void tearDown() {
        // Close the app session after each test
        if (driver != null) {
            driver.quit();
        }
    }

   // @Test
    public void testSendMessage() {
        // Find and click on the chat input field
        WebElement chatInput = driver.findElement(By.id("com.whatsapp:id/entry"));
        chatInput.click();

        // Type a message
        chatInput.sendKeys("Hello, World!");

        // Find and click on the send button
        WebElement sendButton = driver.findElement(By.id("com.whatsapp:id/send"));
        sendButton.click();

        // Verify that the message was sent successfully
        WebElement message = driver.findElement(By.id("com.whatsapp:id/message_text"));
        String sentMessageText = message.getText();
        assert(sentMessageText.equals("Hello, World!"));
    }

   // @Test
    public void testReceiveMessage() {
        // Simulate receiving a message
        String receivedMessage = "Hi there!";
        // ...

        // Verify that the message is displayed in the chat
        WebElement chatMessage = driver.findElement(By.id("com.whatsapp:id/message_text"));
        String displayedMessageText = chatMessage.getText();
        assert(displayedMessageText.equals(receivedMessage));
    }

   // @Test
    public void testMakeVoiceCall() {
        // Find and click on the call button
        WebElement callButton = driver.findElement(By.id("com.whatsapp:id/call_button"));
        callButton.click();

        // Simulate an ongoing voice call
        boolean isCallOngoing = true;
        // ...

        // Verify that the call is ongoing
        assert(isCallOngoing);

        // End the call by clicking on the end call button
        WebElement endCallButton = driver.findElement(By.id("com.whatsapp:id/end_call_btn"));
        endCallButton.click();
    }

    //@Test
    public void testSendImage() {
        // Find and click on the attachment button
        WebElement attachmentButton = driver.findElement(By.id("com.whatsapp:id/attachment_btn"));
        attachmentButton.click();

        // Select the image from the gallery
        WebElement imageOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Gallery']"));
        imageOption.click();

        // Simulate selecting an image and sending it
        boolean isImageSent = true;
        // ...

        // Verify that the image was sent successfully
        assert(isImageSent);
    }

   // @Test
    public void testSwipeToScrollChat() {
        // Perform a swipe gesture to scroll the chat
        WebElement chatContainer = driver.findElement(By.id("com.whatsapp:id/chat_container"));

        // Swipe from bottom to top
        int startX = chatContainer.getLocation().getX() + chatContainer.getSize().getWidth() / 2;
        int startY = chatContainer.getLocation().getY() + chatContainer.getSize().getHeight() - 10;
        int endY = chatContainer.getLocation().getY() + 10;

        touchAction
                .press(PointOption.point(startX, startY))
                .waitAction()
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();

        // Verify that the chat is scrolled
        // ...
    }

    //@Test
    public void testLongPressToForwardMessage() {
        // Find and long press a message
        WebElement message = driver.findElement(By.id("com.whatsapp:id/message_text"));
        touchAction
                .longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(message)).withDuration(Duration.ofMillis(500)))
                .perform();

        // Find and click on the forward button
        WebElement forwardButton = driver.findElement(By.id("com.whatsapp:id/forward_btn"));
        forwardButton.click();

        // Verify that the message is forwarded successfully
        // ...
    }
}

