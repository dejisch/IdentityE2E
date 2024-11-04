package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hooks.BaseHook;
import utilities.VehicleDetailsReader;
import pageObjects.VehicleRegSearchPage;
import pageObjects.VehicleDetailsPage;
import utilities.VehicleDetails;



public class VehicleRegExtractionSteps {
    private WebDriver driver;
    private List<String> regFromFile;
    private Map<String, VehicleDetails> expectedVehicleDetails = new HashMap<>();
    private Map<String, VehicleDetails> actualVehicleDetails = new HashMap<>();

    private VehicleRegSearchPage vehicleRegSearchPage;
    private VehicleDetailsPage vehicleDetailsPage;


    public VehicleRegExtractionSteps(BaseHook baseHook) {
        this.driver = baseHook.getDriver();
        this.vehicleRegSearchPage = new VehicleRegSearchPage(driver);
        this.vehicleDetailsPage = new VehicleDetailsPage(driver);

    }

    @Given("I have the input file {string} and the output file {string}")
    public void i_have_the_input_file_and_the_output_file(String inputFileName, String outputFileName) throws IOException {
        expectedVehicleDetails = VehicleDetailsReader.readCarOutputFile(outputFileName);
        regFromFile = VehicleDetailsReader.extractRegistrations("src/test/resources/testData/" + inputFileName);


    }

    @When("I fetch vehicle details for each registration from the car valuation website")
    public void i_fetch_vehicle_details_for_each_registration_from_the_car_valuation_website() throws InterruptedException {
        for (String registration : regFromFile) {

            vehicleRegSearchPage.enterVehicleDetails(driver, registration, (int) (Math.random() * 100000));
            VehicleDetails vehicleDetails = vehicleDetailsPage.getCarDetails(driver, registration);
            actualVehicleDetails.put(registration, vehicleDetails);
        }
    }

    @Then("the fetched details should match with the output file")
    public void the_fetched_details_should_match_with_the_output_file() {

        for (String vehicleRegistrationNo : regFromFile)
        {
            VehicleDetails expectedResult = expectedVehicleDetails.get(vehicleRegistrationNo);
            VehicleDetails actualResult = actualVehicleDetails.get(vehicleRegistrationNo);
            Assert.assertEquals("Mismatch found in files with Vehicle Registration No: " + vehicleRegistrationNo, expectedResult, actualResult);
        }

    }

}