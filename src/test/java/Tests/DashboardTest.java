package Tests;

import Pages.BaseApplicationPage;
import Pages.DashboardPage;
import Pages.LoginPage;
import com.google.common.collect.ImmutableList;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DashboardTest extends BaseTest {

    private void startLogin() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();

        assertTrue(loginPage.isLoaded());

        loginPage.login();
    }

    @Test
    public void addActivity() throws Exception {
        startLogin();

        DashboardPage dashboardPage = new DashboardPage(driver);
        Thread.sleep(2000);
        assertTrue(dashboardPage.isDisplayed());
        assertTrue(dashboardPage.isTodayButtonDisplayed(),
                "The element wasn't found");
    }

    @Test
    public void getColsSizeOfCalendar() throws Exception {
        startLogin();

        DashboardPage dashboardPage = new DashboardPage(driver);

        Thread.sleep(2000);

        assertTrue(dashboardPage.isDisplayed());
        assertEquals(dashboardPage.getCalendarColsSize(), 7);
    }

    @Test
    public void getDaysNameOfCalendar() throws Exception {
        List<String> expectedLabelsList = ImmutableList.of("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");

        startLogin();

        DashboardPage dashboardPage = new DashboardPage(driver);

        Thread.sleep(2000);

        assertTrue(dashboardPage.isDisplayed());
        assertEquals(dashboardPage.getDaysOfTheWeek(), expectedLabelsList);
    }
}
