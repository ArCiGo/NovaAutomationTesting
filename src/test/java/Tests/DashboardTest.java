package Tests;

import Pages.BaseApplicationPage;
import Pages.DashboardPage;
import Pages.LoginPage;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DashboardTest extends BaseTest {

    private DashboardPage dashboardPage;

    private void startLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        assertTrue(loginPage.isLoaded());
        loginPage.login();

        dashboardPage = new DashboardPage(driver);
    }

    @Test(groups = { "Add activity" })
    public void addActivity() {
        startLogin();
        assertTrue(dashboardPage.isDisplayed());
        dashboardPage.addActivity();
    }

    @Test(groups = { "Calendar" }, dependsOnMethods = { "addActivity" })
    public void getColsSizeOfCalendar() {
        assertTrue(dashboardPage.isDisplayed());
        assertEquals(dashboardPage.getCalendarColsSize(), 7);
    }

    @Test(groups = { "Calendar" }, dependsOnMethods = { "getColsSizeOfCalendar" })
    public void getDaysNameOfCalendar() {
        List<String> expectedLabelsList = ImmutableList.of("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");

        assertTrue(dashboardPage.isDisplayed());
        assertEquals(dashboardPage.getDaysOfTheWeek(), expectedLabelsList);
    }

    @Test(groups = { "Calendar" }, dependsOnMethods = { "getDaysNameOfCalendar" })
    public void getCalendarMonthView() {
        assertTrue(dashboardPage.isDisplayed());
        assertEquals(dashboardPage.calendarMonthView(), 30);
    }
}
