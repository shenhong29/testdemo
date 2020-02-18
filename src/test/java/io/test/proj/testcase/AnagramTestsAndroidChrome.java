/**
 * 
 */
package io.test.proj.testcase;

/**
 * @author honshen
 *
 */
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;





public class AnagramTestsAndroidChrome extends TestTemplate {

    private final AndroidDriver<MobileElement> androidDriver;

    /**
     * @throws MalformedURLException
     * 
     */
    public AnagramTestsAndroidChrome() throws MalformedURLException {
        androidDriver = getAndroidDriverwithChrome();
    }

    private AndroidDriver<MobileElement> getAndroidDriverwithChrome() throws MalformedURLException {
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Pixel 3a API 29");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("appName", "Chrome");
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        capabilities.setCapability("appWaitDuration", "60000");
        
        return new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterTest
    public void finishUp() {
        if (null != androidDriver)
            androidDriver.quit();
    }

    @Test(enabled = true)
    public void getAnagramFromServerByAndroidChrome() {
        androidDriver.findElementById("com.android.chrome:id/terms_accept").click();

        myDelay(1000l);
        androidDriver.findElementById("com.android.chrome:id/negative_button").click();

        myDelay(2000l);
        androidDriver.get("https://wordsmith.org/anagram/");
        myDelay(5000l);
        String xpath = "//android.view.View//android.widget.EditText";
        androidDriver.findElementByXPath(xpath).sendKeys(myAnagram);
        androidDriver.findElementByClassName("android.widget.Button").click();
        myDelay(20000l);

        List<String> anagrams = androidDriver.findElementsByClassName("android.view.View").stream()
                .map(e -> e.getText().trim()).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        saveAnagrams(anagrams);

    }

    /**
     * com.android.chrome/com.google.android.apps.chrome.Main
     * 
     * { "deviceName": "Pixel 3a API 29", "platformName": "Android", "appName":
     * "Calculator", "appPackage": "com.android.chrome", "appActivity":
     * "com.google.android.apps.chrome.Main" }
     */
}

