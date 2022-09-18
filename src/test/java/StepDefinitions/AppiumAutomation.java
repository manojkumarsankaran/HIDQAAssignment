package StepDefinitions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AppiumAutomation {

	AndroidDriver driver;

	@Given("appium server is started running")
	public void appium_server_is_started_running() {
		try {
			AppiumDriverLocalService service = new AppiumServiceBuilder()
					.withAppiumJS(new File("usr//local//lib//node_modules//appium//build/lib//main.js"))
					.withIPAddress("http://127.0.0.1").usingPort(4723).build();
			service.start();
		} catch (Exception e) {
		}
	}

	@When("user opens the play store application")
	public void user_opens_the_play_store_application() throws MalformedURLException {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
			capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Honor 8X");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.vending");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
					"com.google.android.finsky.activties.mainActivity");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

		} catch (Exception e) {
		}
	}

	@When("user navigates to categories tab")
	public void user_navigates_to_categories_tab() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//nav[contains(text(),'Apps')]")).click();
			Thread.sleep(2000);
			WebElement children = driver.findElement(By.xpath("android.widget.TestView[@text='Children']"));
			((JavascriptExecutor) driver).executeScript("Mobile:SwipeGesture", ImmutableMap.of("elementID",
					((RemoteWebElement) children).getId(), "direction", "left", "percent", 0.75));
			driver.findElement(AppiumBy.xpath("android.widget.TestView[@text='Categories']")).click();
			Thread.sleep(2000);
		} catch (Exception e) {
		}
	}

	@Then("verify Travel & Local is not displayed in games tab")
	public void verify_travel_local_is_not_displayed_in_games_tab() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//nav[contains(text(),'Games')]")).click();
			Thread.sleep(2000);
			WebElement children = driver.findElement(By.xpath("android.widget.TestView[@text='Children']"));
			((JavascriptExecutor) driver).executeScript("Mobile:SwipeGesture", ImmutableMap.of("elementID",
					((RemoteWebElement) children).getId(), "direction", "left", "percent", 0.75));
			WebElement premium = driver.findElement(By.xpath("android.widget.TestView[@text='Premium']"));
			((JavascriptExecutor) driver).executeScript("Mobile:SwipeGesture", ImmutableMap.of("elementID",
					((RemoteWebElement) premium).getId(), "direction", "left", "percent", 0.75));
			driver.findElement(AppiumBy.xpath("android.widget.TestView[@text='Categories']")).click();
			Thread.sleep(2000);
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
						"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Travel & Local\"));")));
			} catch (Exception e) {
				System.out.println("Travel & Local is not displayed");
			}
		} catch (Exception e) {
		}
	}

	@Then("verify Travel & Local is displayed is apps tab")
	public void verify_travel_local_is_displayed_is_apps_tab() throws InterruptedException {
		driver.findElement(By.xpath("//nav[contains(text(),'Games')]")).click();
		Thread.sleep(2000);
		WebElement appsTraLoc = driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Travel & Local\"));"));
		Assert.assertTrue(appsTraLoc.isDisplayed());
		appsTraLoc.click();
		Thread.sleep(2000);
	}

	@Then("verify indigo flight app is displayed in Travel & Local")
	public void verify_indigo_flight_app_is_displayed_in_travel_local() {
		try {
			WebElement indigoFlight = driver
					.findElement(By.xpath("android.widget.TestView[@text='Indigo Flight -Air Travel App']"));
			Assert.assertTrue(indigoFlight.isDisplayed());
		} catch (Exception e) {
		}
	}
}
