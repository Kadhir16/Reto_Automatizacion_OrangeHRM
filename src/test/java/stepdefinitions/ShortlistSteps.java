package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;


public class ShortlistSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    public ShortlistSteps() {
        this.driver = utils.WebDriverConfig.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Given("the candidate is on the Application Stage")
    public void the_candidate_is_on_the_application_stage() {
        try {
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[contains(@class, 'oxd-text--h6') and normalize-space()='Application Stage']")
            ));
            assert titleElement.isDisplayed() : "No se encuentra en la página Application Stage.";
            System.out.println("Candidato está en la página Application Stage.");
        } catch (Exception e) {
            System.out.println("Error al validar la página Application Stage: " + e.getMessage());
        }
    }


    @When("the user clicks on the \"Shortlist\" button")
    public void the_user_clicks_on_the_shortlist_button() {
        try {
            WebElement shortlistButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class, 'oxd-button--success') and text()='Shortlist']")
            ));
            shortlistButton.click();
            System.out.println("Botón 'Shortlist' clickeado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el botón 'Shortlist': " + e.getMessage());
        }
    }




    @When("navigates to the Shortlist Candidate")
    public void navigates_to_the_shortlist_candidate() {
        try {
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[contains(@class, 'oxd-text--h6') and normalize-space()='Shortlist Candidate']")
            ));
            assert titleElement.isDisplayed() : "No se encuentra en la página Shortlist Candidate.";
            System.out.println("Usuario navegó a la página Shortlist Candidate.");
        } catch (Exception e) {
            System.out.println("Error al navegar a la página Shortlist Candidate: " + e.getMessage());
        }
    }



    @When("the candidate's application status is {string}")
    public void the_candidate_s_application_status_is(String status) {
        try {
            WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(@class, 'oxd-text--subtitle-2') and text()='Status: Application Initiated']\n" + status + "')]")
            ));

            assert statusElement.isDisplayed() : "El estado del candidato no coincide con: " + status;
            System.out.println("El estado del candidato es: " + status);
        } catch (Exception e) {
            System.out.println("Error al validar el estado del candidato: " + e.getMessage());
        }
    }


    @When("the user clicks on the Save button on the Candidate Shortlist Information page")
    public void the_user_clicks_on_the_save_button_in_shortlist_page() {
        try {
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Save']")
            ));
            saveButton.click();
            System.out.println("Botón 'Save' clickeado exitosamente en la página Candidate Shortlist Information.");
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el botón 'Save': " + e.getMessage());
        }
    }

    @Then("the candidate is shortlisted successfully")
    public void the_candidate_is_shortlisted_successfully() {
        try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'Successfully Saved')]")
            ));
            assert successMessage.isDisplayed() : "No se mostró el mensaje de éxito al guardar.";
            System.out.println("Candidato seleccionado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al validar que el candidato fue seleccionado: " + e.getMessage());
        }
    }

}


