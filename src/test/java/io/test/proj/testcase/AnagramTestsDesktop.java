/**
 * 
 */
package io.test.proj.testcase;

/**
 * @author honshen
 *
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class AnagramTestsDesktop extends TestTemplate {

    private final WebDriver webDriver;

    public AnagramTestsDesktop() throws MalformedURLException {

        webDriver = creatWebDriver();
    }

    private WebDriver creatWebDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.MAC);
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability("takesScreenshot", false);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-web-security");
        options.addArguments("no-proxy-server");
        options.addArguments("ignore-certificate-errors");
        options.addArguments("disable-popup-blocking");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        capabilities.setCapability("goog:chromeOptions", options);
        return new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
    }

    @AfterTest
    public void finishUp() {
        if (null != webDriver)
            webDriver.quit();
    }

    @Test
    public void getAnagramFromServerByDesktopChrome() {

        webDriver.get("https://wordsmith.org/anagram/");
        myDelay(2000l);
        webDriver.findElement(By.name("anagram")).sendKeys(myAnagram);
        webDriver.findElement(By.cssSelector("input[value='Get anagrams']")).click();
        myDelay(5000l);
        String[] myAnagrams = webDriver.findElement(By.cssSelector("div.p402_premium")).getText().split("\\r?\\n");
        ;
        saveAnagrams(Arrays.asList(myAnagrams));

    }
}
