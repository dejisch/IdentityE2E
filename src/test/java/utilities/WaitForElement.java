package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitForElement {

    public static void fluentWait(By byElement, WebDriver driver)
    {
        try {

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))          // Maximum wait time
                .pollingEvery(Duration.ofSeconds(5))          // Polling interval
                .ignoring(NoSuchElementException.class);      // Ignore NoSuchElementException during polling

        wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
    }
        catch(Exception e)
    {

    }
    }

    public static void waitForElementVisible(WebDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        catch(Exception e)
        {

        }
    }


    public static void waitForPageToLoad(WebDriver driver, int timeout) {
        try {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until((ExpectedCondition<Boolean>) webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        }
        catch(Exception e)
        {

        }
    }
}
