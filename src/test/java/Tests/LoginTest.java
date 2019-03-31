package Tests;

import Pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void LoginWithValidCredentials() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.goTo();
        assertTrue(loginPage.isLoaded());
        loginPage.login();
    }
}
