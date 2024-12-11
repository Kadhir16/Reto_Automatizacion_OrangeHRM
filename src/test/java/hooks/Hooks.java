package hooks;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import utils.WebDriverConfig;

import java.util.Scanner;

public class Hooks {

    @BeforeAll
    public static void setUp() {
        WebDriverConfig.getDriver();
        System.out.println("WebDriver inicializado para toda la ejecución.");
    }

    @AfterAll
    public static void tearDown() {
        // Preguntar si el navegador debe cerrarse
        System.out.println("¿Desea cerrar el navegador? (sí/no):");
        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine().trim().toLowerCase();

        if (respuesta.equals("sí") || respuesta.equals("si")) {
            WebDriverConfig.closeDriver();
            System.out.println("WebDriver cerrado después de toda la ejecución.");
        } else {
            System.out.println("El navegador permanecerá abierto para inspección.");
        }
    }
}



