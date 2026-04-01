package steps;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import pages.CatalogoPage;
import pages.LoginPage;
import support.TestContext;
import org.junit.jupiter.api.Assertions;

import java.util.ResourceBundle;

public class LoginSteps {
    private final LoginPage loginPage;
    private final CatalogoPage catalogoPage;
    private ResourceBundle config;

    public LoginSteps(TestContext context) {
        this.loginPage = new LoginPage(context.page);
        this.catalogoPage = new CatalogoPage(context.page);

        try {
            this.config = ResourceBundle.getBundle("config");
        } catch (Exception e) {
            System.out.println("No se encontró config.properties local.");
        }
    }

    private String getCredencialEstricta(String key) {
        String envVar = System.getenv(key);
        if (envVar != null && !envVar.trim().isEmpty()) return envVar;
        try {
            if (config != null && config.containsKey(key)) {
                return config.getString(key);
            }
        } catch (Exception e) {
        }

        throw new RuntimeException("ERROR DE SEGURIDAD: Falta configurar la variable [" + key + "] en config.properties o en el entorno.");
    }

    @Dado("que el usuario navega a la pagina de Sauce Demo")
    public void navegarASauceDemo() {
        String url = getCredencialEstricta("BASE_URL");
        loginPage.navigate(url);
    }

    @Dado("que el usuario inicia sesion con {string}")
    public void loginExitosoBackground(String username) {
        String url = getCredencialEstricta("BASE_URL");
        String password = getCredencialEstricta("PASSWORD");

        loginPage.navigate(url);
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickLogin();
    }

    @Cuando("ingresa el usuario {string} y su password")
    public void ingresarCredenciales(String username) {
        String password = getCredencialEstricta("PASSWORD");

        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
    }

    @Cuando("hace clic en el boton Login")
    public void clicLogin() {
        loginPage.clickLogin();
    }

    @Entonces("debería visualizar el catálogo de productos")
    public void validarCatalogoVisible() {
        catalogoPage.verifyCatalogIsVisible();
    }

    @Entonces("debería ver un mensaje de error indicando que el usuario esta bloqueado")
    public void validarErrorBloqueado() {
        Assertions.assertTrue(loginPage.getErrorMessage().contains("locked out"), "El mensaje de error esperado no apareció.");
    }
}