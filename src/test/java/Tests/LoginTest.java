package Tests;

import Pages.DashboardPage;
import Pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utilities.ExtentReports.ExtentTestManager;

import java.lang.reflect.Method;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(groups = { "Login" })
    public void LoginWithValidCredentials(Method method) throws Exception {
        //ExtentTestManager.getTest().setDescription("Valid login scenario");
        ExtentTestManager.startTest(method.getName(), "Login with valid credentials");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        assertTrue(loginPage.isLoaded());
        loginPage.login();

        DashboardPage dashboardPage = new DashboardPage(driver);
        assertTrue(dashboardPage.isDisplayed());
    }
}
