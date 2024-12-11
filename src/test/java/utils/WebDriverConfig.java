package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;

public class WebDriverConfig {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String driverPath = "C:\\Automation\\Tools\\chromedriver-win64\\chromedriver.exe";
            File driverFile = new File(driverPath);

            if (!driverFile.exists()) {
                throw new RuntimeException("El archivo ChromeDriver no existe en la ruta especificada: " + driverPath);
            }

            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();

            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); // Ajusta 30 a tus necesidades
            driver.manage().window().maximize();

            System.out.println("WebDriver inicializado correctamente.");
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Marca como null para reiniciar en la próxima ejecución
            System.out.println("WebDriver cerrado correctamente.");
        }
    }
}





