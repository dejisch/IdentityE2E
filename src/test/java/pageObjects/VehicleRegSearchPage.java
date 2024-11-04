package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WaitForElement;

public class VehicleRegSearchPage {
    public WebDriver driver;

    public By handler = By.id("onetrust-accept-btn-handler");
    public By vehicleReg =  By.id("vehicleReg");
    public VehicleRegSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public  void enterVehicleDetails(WebDriver driver, String carReg, int mileage) throws InterruptedException {
        driver.get("https://webuyanycar.com/");
        try{
            WaitForElement.fluentWait(handler, driver);

            driver.findElement(handler).click();
        }
        catch(Exception ignored) {
        }
        WaitForElement.waitForElementVisible(driver, vehicleReg, 10);
        WaitForElement.fluentWait(vehicleReg, driver);
        driver.findElement(vehicleReg).sendKeys(carReg.replaceAll(" ", ""));

        driver.findElement(By.id("Mileage")).sendKeys(String.valueOf(mileage));

        driver.findElement(By.id("btn-go")).click();
    }

}
