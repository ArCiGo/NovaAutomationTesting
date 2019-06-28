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
import utilities.ExtentReports.ExtentTestManager;

import java.lang.reflect.Method;
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
    public void addActivity(Method method) {
        ExtentTestManager.startTest(method.getName(), "Adding an activity");

        String projectOptionValue = "iTexico - Talent Management",
                categoryOptionValue = "Training & Development (not project related)",
                hourValue = "8",
                ticketValue = "Testing something amazing",
                commentsValue = "Something amazing";

        startLogin();
        assertTrue(dashboardPage.isDisplayed());
        List<String> activityList = dashboardPage.addActivity(projectOptionValue, categoryOptionValue, hourValue,ticketValue , commentsValue);

        dashboardPage.clickOnSelectedActivity(activityList.get(0), activityList.get(1));
        dashboardPage.validateActivity(projectOptionValue, categoryOptionValue, hourValue,ticketValue , commentsValue);
    }

    @Test(groups = { "Calendar" }, dependsOnMethods = { "addActivity" })
    public void getColsSizeOfCalendar(Method method) {
        ExtentTestManager.startTest(method.getName(), "Getting the cols size of calendar");

        assertTrue(dashboardPage.isDisplayed());
        assertEquals(dashboardPage.getCalendarColsSize(), 7);
    }

    @Test(groups = { "Calendar" }, dependsOnMethods = { "getColsSizeOfCalendar" })
    public void getDaysNameOfCalendar(Method method) {
        ExtentTestManager.startTest(method.getName(), "Getting the days name of week");

        List<String> expectedLabelsList = ImmutableList.of("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");

        assertTrue(dashboardPage.isDisplayed());
        assertEquals(dashboardPage.getDaysOfTheWeek(), expectedLabelsList);
    }

    @Test(groups = { "Calendar" }, dependsOnMethods = { "getDaysNameOfCalendar" })
    public void getCalendarMonthView(Method method) {
        ExtentTestManager.startTest(method.getName(), "Getting the days of the month");

        assertTrue(dashboardPage.isDisplayed());
        assertEquals(dashboardPage.calendarMonthView(), 31);
    }
}
