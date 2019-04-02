package Tests;

import Pages.BaseApplicationPage;
import Pages.DashboardPage;
import Pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class DashboardTest extends BaseTest {

    @Test
    public void addActivity() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.goTo();
        assertTrue(loginPage.isLoaded());

        loginPage.login();

        DashboardPage dashboardPage = new DashboardPage(driver);

        assertTrue(dashboardPage.isDisplayed());
        assertTrue(dashboardPage.isTodayButtonDisplayed(),
                "The element wasn't found");
    }
}
