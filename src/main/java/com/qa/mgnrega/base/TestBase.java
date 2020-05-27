package com.qa.mgnrega.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.qa.mgnrega.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	
	private static String strWebPortalStartTime = null;
	private static String strWebPortalStopTime = null;	
	static float strWebPortaltimeElapsed ;
	static String  strWebPortalExecutionTime ="";
	
	static TestUtil testutil = new TestUtil();

	public static String strAbsolutepath = new File("").getAbsolutePath() + "\\";

	public TestBase() {

		try {

			prop = new Properties();

			FileInputStream fpi = new FileInputStream(
					strAbsolutepath + "src\\main\\java\\com\\qa\\mgnrega\\config\\config.properties");

			prop.load(fpi);

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public static String initializations() {
		
		try {

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("window-size=1400,800");

			opt.addArguments("--headless");

			WebDriverManager.chromedriver().version("2.40").setup();
			
			driver = new ChromeDriver(opt);

		}

		else if (browserName.equalsIgnoreCase("ff")) {

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("IE")) {
			/*
			 * System.setProperty("webdriver.ie.driver", strAbsolutepath +
			 * "BrowserJars\\IEDriver.exe");
			 */
			WebDriverManager.iedriver().setup();

			driver = new InternetExplorerDriver();
		}

		else {
			System.out.println("Invalid Browser !!!! Value from Config File  : " + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		strWebPortalStartTime = testutil.start();
		
		driver.get(prop.getProperty("url"));
		
		strWebPortalStopTime = testutil.stop();
		
		strWebPortaltimeElapsed = testutil.getElapsedTime();

		strWebPortalExecutionTime = testutil.ExecutionTime(strWebPortaltimeElapsed);
		}
		catch(Exception e)
		{
			System.out.println(e);
						
			strWebPortalStopTime = testutil.stop();
			
			strWebPortaltimeElapsed = testutil.getElapsedTime();

			strWebPortalExecutionTime = testutil.ExecutionTime(strWebPortaltimeElapsed);

		}


		return strWebPortalExecutionTime;
	}

	public void scrolToElement(WebElement element)

	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

	}
	

	public void getScreenshot(String filename) {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		try {
			ImageIO.write(screenshot.getImage(), "PNG",
					new File(strAbsolutepath + "\\Screenshot\\" + filename + ".png"));
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getScreenshot(WebElement element, String filename) {

		//scrolToElement(element);

		File scrFile = element.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(strAbsolutepath + "\\Screenshot\\" + filename + ".png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		// return AddNewCustomerLabel.isDisplayed();
	}

	/*
	 * public void getScreenshot(String filename) {
	 * 
	 * // scrolToElement(element);
	 * 
	 * File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	 * 
	 * try { FileUtils.copyFile(scrFile, new File(strAbsolutepath +
	 * "\\Screenshot\\" + filename + ".png"));
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * // return AddNewCustomerLabel.isDisplayed(); }
	 */

}