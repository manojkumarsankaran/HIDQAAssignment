package StepDefinitions;

import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UIAutomation {

	WebDriver driver;

	@Given("user launches the react jS URL")
	public void user_launches_the_react_j_s_url() throws InterruptedException {
		try {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\manoj\\Worspace1\\CucumberTest\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("https://reactjs.org/");
			Thread.sleep(5000);
			driver.manage().window().maximize();
		} catch (Exception e) {
		}
	}

	public boolean waitForElementToBeVisible(String element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		return true;
	}

	@When("user is navigated to Docs page")
	public void user_is_navigated_to_docs_page() {
		driver.findElement(By.xpath("//a[contains(text(),'Docs')]")).click();
		waitForElementToBeVisible("//div[contains(text(),'Main')]");
	}

	@When("user expands the Main concepts")
	public void user_expands_the_main_concepts() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//div[contains(text(),'Main')]")).click();
			Thread.sleep(3000);
		} catch (Exception e) {
		}
	}

	@Then("Main concepts sub elements text should be saved in file")
	public void main_concepts_sub_elements_text_should_be_saved_in_file() throws IOException {
		List<WebElement> list = driver.findElements(By.xpath("(//ul[contains(@id,'section')])[2]//li//a"));
		try {
			File myObj = new File("D:\\Files");
			if (myObj.mkdir()) {
				System.out.print("folder created");
			}
			File myObj1 = new File("D:\\Files\\MainConcepts.txt");
			if (myObj1.createNewFile()) {
				System.out.print("file created");
			}
			FileWriter fw = new FileWriter("D:\\Files\\MainConcepts.txt");
			System.out.print("file opened");
			for (WebElement ele : list) {
				String text = ele.getText();
				fw.write(text + "\n");
			}
			fw.close();
			driver.findElement(By.xpath("//div[contains(text(),'Main')]")).click();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		driver.quit();
	}

	@When("user expands the Advanced Guides")
	public void user_expands_the_advanced_guides() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//div[contains(text(),'Advanced')]")).click();
			Thread.sleep(3000);
		} catch (Exception e) {
		}
	}

	@Then("Advanced Guides sub elements text should be saved in file")
	public void advanced_guidessub_elements_text_should_be_saved_in_file() throws IOException {
		List<WebElement> list = driver.findElements(By.xpath("(//ul[contains(@id,'section')])[3]//li//a"));
		try {
			File myObj1 = new File("D:\\Files\\AdvancedGuides.txt");
			myObj1.createNewFile();
			FileWriter fw = new FileWriter("D:\\Files\\AdvancedGuides.txt");
			for (WebElement ele : list) {
				String text = ele.getText();
				fw.write(text + "\n");
			}
			fw.close();
			driver.findElement(By.xpath("//div[contains(text(),'Advanced')]")).click();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		driver.quit();
	}

	@When("user is navigated to tutorials page")
	public void user_is_navigated_to_tutorials_page() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//nav//a[contains(text(),'Tutorial')]")).click();
			Thread.sleep(2000);
			waitForElementToBeVisible("//h1[contains(text(),'Intro')]");
		} catch (Exception e) {
		}
	}

	@When("user scrolls down the page")
	public void user_scrolls_down_the_page() throws InterruptedException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(10000);
		} catch (Exception e) {
		}
	}

	@Then("verify respected content is bolded and blue color line is displayed")
	public void verify_respected_content_is_bolded_and_blue_color_line_is_displayed() {
		try {
			String contentText = driver.findElement(By.xpath("((//h3)|(//h2))[2]")).getText();
			String highLightedText = driver.findElement(By.xpath("(//ul[contains(@id,'section')]//li//a)[2]"))
					.getText();
			Assert.assertTrue(contentText.equals(highLightedText));
			String boldedText = driver.findElement(By.xpath("(//ul[contains(@id,'section')]//li//a)[2]"))
					.getCssValue("font-weight");
			Assert.assertTrue(Integer.parseInt(boldedText) == 700);
			String color = driver.findElement(By.xpath("//ul[contains(@id,'section')]//li//a//span"))
					.getCssValue("color");
			String hex = Color.fromString(color).asHex();
			System.out.println(hex);
			Assert.assertTrue(hex.equals("#1a1a1a"));
			driver.quit();
		} catch (Exception e) {
		}
	}

}
