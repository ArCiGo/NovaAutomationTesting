package Tests;

import Pages.DashboardPage;
import Pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(groups = { "Login" })
    public void LoginWithValidCredentials() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        assertTrue(loginPage.isLoaded());
        loginPage.login();

        DashboardPage dashboardPage = new DashboardPage(driver);
        assertTrue(dashboardPage.isDisplayed());
    }
}
