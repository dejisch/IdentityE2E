package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.VehicleDetails;
import utilities.WaitForElement;

import java.util.List;

public class VehicleDetailsPage {
    public By vehicleDetail = By.xpath("//*[@class=\"d-table-cell value\"]");
    public WebDriver driver;
    String make=null, model=null, year ="0";

    public VehicleDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public VehicleDetails getCarDetails(WebDriver driver, String vehicleReg) throws InterruptedException {

        try {

            WaitForElement.fluentWait(vehicleDetail, driver);

            List<WebElement> elements = driver.findElements(vehicleDetail);
            if(!elements.isEmpty()) {
                make = elements.get(7).getText();
                model = elements.get(8).getText();
                year = elements.get(9).getText();
                return new VehicleDetails(vehicleReg, make, model, Integer.parseInt(year));

            }

        }
        catch (Exception ignored)
        {

        }
        return null;
    }
}
