package Tests;

import Pages.DashboardPage;
import Pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private WebDriverWait wait;

    @Test(groups = "Login")
    public void LoginWithValidCredentials() throws Exception {
        wait = new WebDriverWait(driver, 1);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        assertTrue(loginPage.isLoaded());
        loginPage.login();

        DashboardPage dashboardPage = new DashboardPage(driver);
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.iTexicoLogo));
    }
}
