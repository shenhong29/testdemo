/**
 * 
 */
package io.test.proj.testcase;

/**
 * @author honshen
 *
 */
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;



public class AnagramTestsIOSSafari extends TestTemplate {

    private final IOSDriver<MobileElement> iOSDriver;

    public AnagramTestsIOSSafari() throws MalformedURLException {
        iOSDriver = createiOSDriverWithSafari();
    }

    private IOSDriver<MobileElement> createiOSDriverWithSafari() throws MalformedURLException {
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("deviceName", "iPad Air (3rd generation)");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "13.3");
        capabilities.setCapability("platformSimCode", "17C45");
        capabilities.setCapability("bundleId", "com.apple.mobilesafari");
        capabilities.setCapability("appWaitDuration", "60000");
        
        return new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @AfterTest
    public void finishUp() {

        if (null != iOSDriver)
            iOSDriver.quit();
    }

    @Test
    public void getAnagramFromServerByIOSSafari() {

        myDelay(2000l);
        iOSDriver.get("https://wordsmith.org/anagram/");
        myDelay(5000l);
        String xpath = "//XCUIElementTypeOther//XCUIElementTypeTextField";

        iOSDriver.findElementByXPath(xpath).sendKeys(myAnagram);
        iOSDriver.findElementByAccessibilityId("Get anagrams").click();
        myDelay(20000l);

        List<String> anagrams = iOSDriver.findElementsByXPath("//XCUIElementTypeStaticText[@name]").stream()
                .map(e -> e.getText().trim()).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        saveAnagrams(anagrams);

    }
}
