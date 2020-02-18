/**
 * 
 */
package io.test.proj.testcase;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author honshen
 *
 */
public class AndroidCalculatorTest {

	private AndroidDriver<MobileElement> andriodDriver;
	/**
	 * 
	 */
	public AndroidCalculatorTest() {
		// nothing
	}

	
	@BeforeClass
	public void setUp() throws MalformedURLException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("uuid", "emulator-5554");
		capabilities.setCapability("deviceName", "Pixel 3a API 29");
		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("appName", "Calculator");
		capabilities.setCapability("appPackage", "com.google.android.calculator");
		// This package name of your app (you can get it from apk info app) adb shell, dumpsys window windows | grep -i Calcualtor
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator"); 
		capabilities.setCapability("appWaitActivity", "com.android.calculator2.Calculator");
		capabilities.setCapability("appWaitDuration", "60000");
		// It will launch the Calculator App in Android Device using the configurations
		// specified in Desired Capabilities
		andriodDriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@AfterClass
	public void teardown() {
		// close the app
		andriodDriver.quit();
	}
	
	@Test
	public void testCalculator() {
		
		int a = 322;
		int b = 122;
		int c = a + b;
		myDelay(2000l);
		enterDigits(a);

		myDelay(1000l);
		
		getOperationMobileElement("add").click();
		
		myDelay(1000l);
		enterDigits(b);

		myDelay(1000l);
		andriodDriver.findElementById("com.google.android.calculator:id/eq").click();		
		
		myDelay(2000l);
		MobileElement results = andriodDriver.findElementByClassName("android.widget.TextView");
		
		assert results.getText().equals("" + c) : "Actual value is : " + results.getText()
				+ " did not match with expected value:  " + c;
		System.out.println("results: " + a + " + " + b + " = " + c);
	}

	private void enterDigits(final int input) {
		String myInput = "" + input;
		
		char[] myInputChars = myInput.toCharArray();
		
		for(char c : myInputChars) {
			getDigitMobileElement(c).click();
		}
	}
	
	
	private MobileElement getDigitMobileElement(final char digit) {

		String myId = "com.google.android.calculator:id/digit_" + digit;
		
		return andriodDriver.findElementById(myId);
	}

	private MobileElement getOperationMobileElement(final String ops) {
		/**
		 * op: mul *; div /; sub -; add +
		 * 
		 */
		String myId = "com.google.android.calculator:id/op_" + ops;

		return andriodDriver.findElementById(myId);
	}

	private void myDelay(final long d) {

		try {
			Thread.sleep(d);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * { "deviceName": "Pixel 3a API 29", "platformName": "Android", "appName":
	 * "Calculator", "appPackage": "com.google.android.calculator", "appActivity":
	 * "com.android.calculator2.Calculator" }
	 */
}
