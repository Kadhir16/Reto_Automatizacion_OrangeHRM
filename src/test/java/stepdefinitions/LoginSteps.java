package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.time.Duration;
import utils.WebDriverConfig;

public class LoginSteps {

    private final WebDriver driver = WebDriverConfig.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @Given("the user opens the OrangeHRM login page")
    public void the_user_opens_the_orange_hrm_login_page() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println("Página de inicio de sesión de OrangeHRM abierta.");
    }

    @When("the user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {
        try {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));

            usernameField.sendKeys("Admin");
            passwordField.sendKeys("admin123");
            loginButton.click();
            System.out.println("Credenciales ingresadas y botón de inicio de sesión presionado.");
        } catch (Exception e) {
            System.out.println("Error durante el ingreso de credenciales: " + e.getMessage());
        }
    }


    @When("the user navigates to the Recruitment section")
    public void the_user_navigates_to_the_recruitment_section() {
        WebElement recruitmentMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/web/index.php/recruitment/viewRecruitmentModule']")));
        recruitmentMenu.click();
        System.out.println("Usuario navegó al módulo de Recruitment.");
    }
}

