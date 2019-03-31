package Tests;

import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void LoginWithValidCredentials() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.goTo();
        loginPage.login();
    }
}
