package hooks;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;


public class BaseHook {
    public static WebDriver driver;

    @Before
    public void setup()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }


    @AfterStep
    public static void takeScreenshot(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "image");
    }

    @After
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
        }

    }

    public WebDriver getDriver() {
        return driver;
    }
}
