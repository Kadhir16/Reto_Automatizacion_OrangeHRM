package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Map;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import utils.WebDriverConfig;

public class RecruitmentSteps {

    private final WebDriver driver = WebDriverConfig.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @Given("the user is logged in and on the Recruitment module")
    public void the_user_is_logged_in_and_on_the_recruitment_module() {
        try {
            // Validar que el usuario esté logueado (por ejemplo, verificando la visibilidad del dashboard)
            WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[text()='Dashboard']")));
            assert dashboardElement.isDisplayed() : "El usuario no está logueado.";

            // Navegar al módulo de Recruitment
            WebElement recruitmentMenu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/web/index.php/recruitment/viewRecruitmentModule']")));
            recruitmentMenu.click();

            System.out.println("El usuario está logueado y en el módulo de Recruitment.");
        } catch (Exception e) {
            System.out.println("Error al navegar al módulo de Recruitment: " + e.getMessage());
            throw e; // Propagar la excepción si ocurre un error
        }
    }


    @When("the user clicks on the \"+ Add\" button")
    public void the_user_clicks_on_the_add_button() {
        try {
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class, 'oxd-button--secondary') and normalize-space()='Add']")));
            addButton.click();
            System.out.println("Botón '+ Add' clickeado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el botón '+ Add': " + e.getMessage());
        }
    }

    @When("fills in the candidate's details:")
    public void fills_in_the_candidate_s_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> candidateDetails = dataTable.asMap(String.class, String.class);
        try {
            // Llenar First Name
            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='First Name']")));
            firstName.sendKeys(candidateDetails.get("First Name"));

            // Llenar Middle Name
            WebElement middleName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Middle Name']")));
            middleName.sendKeys(candidateDetails.get("Middle Name"));

            // Llenar Last Name
            WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Last Name']")));
            lastName.sendKeys(candidateDetails.get("Last Name"));

            // Seleccionar Vacancy
            WebElement vacancyDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'oxd-select-text') and contains(.,'-- Select --')]")));
            vacancyDropdown.click();

            WebElement vacancyOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@role='option' and contains(.,'" + candidateDetails.get("Vacancy") + "')]")));
            vacancyOption.click();

            // Llenar Email
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[text()='Email']/following::input")));
            emailField.sendKeys(candidateDetails.get("Email"));

            // Llenar Contact Number
            WebElement contactNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[text()='Contact Number']/following::input")));
            contactNumberField.sendKeys(candidateDetails.get("Contact Number"));

            // Subir Resume
            WebElement resumeUpload = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[type='file']")));
            String resumePath = System.getProperty("user.dir") + "/src/test/resources/features/files/" + candidateDetails.get("Resume");
            resumeUpload.sendKeys(resumePath);

            // Llenar Keywords
            WebElement keywordsField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[text()='Keywords']/following::input")));
            keywordsField.sendKeys(candidateDetails.get("Keywords"));

// Llenar Date of Application
            WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[text()='Date of Application']/following::input")));
// Asegurarse de que el campo esté limpio antes de ingresar la fecha
            dateField.click(); // Si es necesario abrir un calendario
            dateField.clear(); // Limpiar el contenido actual del campo
            dateField.sendKeys(candidateDetails.get("Date of Application"));
            dateField.sendKeys("\uE007"); // Enviar tecla ENTER para confirmar (Unicode para tecla Enter)


            // Llenar Notes
            WebElement notesField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[text()='Notes']/following::textarea")));
            notesField.sendKeys(candidateDetails.get("Notes"));

            System.out.println("Formulario completado con datos del candidato.");
        } catch (Exception e) {
            System.out.println("Error al completar el formulario: " + e.getMessage());
            throw e; // Relanzar la excepción para depuración
        }
    }






    @When("clicks the {string} button")
    public void clicks_the_button(String buttonText) {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='" + buttonText + "']")));
            button.click();
            System.out.println("Botón '" + buttonText + "' clickeado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el botón '" + buttonText + "': " + e.getMessage());
        }
    }

    @Then("the candidate is added successfully")
    public void the_candidate_is_added_successfully() {
        try {
            // Esperar que el mensaje de éxito esté presente en el DOM
            WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(text(),'Successfully Saved')]")));

            // Verificar si el mensaje está presente
            assert successMessage.isDisplayed() : "El mensaje de éxito no se mostró correctamente.";
            System.out.println("El candidato fue agregado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al verificar el mensaje de éxito: " + e.getMessage());
        }
    }


    @Then("the user is redirected to the Application Stage Information page")
    public void the_user_is_redirected_to_the_application_stage_information_page() {
        try {
            // Esperar a que el encabezado sea visible
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[contains(@class, 'oxd-text--h6') and normalize-space()='Application Stage']")
            ));
            assert titleElement.isDisplayed() : "No se encuentra en la página Application Stage Information.";
            System.out.println("Usuario redirigido a la página Application Stage Information.");
        } catch (Exception e) {
            System.out.println("Error al validar la redirección a la página Application Stage Information: " + e.getMessage());
            throw e; // Relanzar excepción
        }
    }



}
