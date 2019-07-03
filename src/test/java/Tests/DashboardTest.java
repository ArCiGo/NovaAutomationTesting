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
import utilities.Log;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DashboardTest extends BaseTest {

    private DashboardPage dashboardPage;

    String projectOptionValue = "iTexico - Talent Management",
            categoryOptionValue = "Training & Development (not project related)",
            hourValue = "8",
            ticketValue = "Testing something amazing",
            commentsValue = "Something amazing";

    private void startLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        assertTrue(loginPage.isLoaded());
        loginPage.login();

        dashboardPage = new DashboardPage(driver);
    }

    @Test(groups = {"Add activity"})
    public void addActivity(Method method) {
        ExtentTestManager.startTest(method.getName(), "Adding an activity");

        startLogin();
        assertTrue(dashboardPage.isDisplayed());

        dashboardPage.openActivityModal();
        List<String> activityList = dashboardPage.addActivity(projectOptionValue, categoryOptionValue, hourValue, ticketValue, commentsValue);

        dashboardPage.clickOnSelectedActivity(activityList.get(0), activityList.get(1));
        dashboardPage.validateActivity(projectOptionValue, categoryOptionValue, hourValue, ticketValue, commentsValue);
        dashboardPage.closeActivityModal();
    }

    @Test(groups = {"Update activity"})
    public void updateActivity(Method method) {
        ExtentTestManager.startTest(method.getName(), "Update an activity");

        startLogin();
        assertTrue(dashboardPage.isDisplayed());

        // Creation of activity

        dashboardPage.openActivityModal();
        List<String> activityList = dashboardPage.addActivity(projectOptionValue, categoryOptionValue, hourValue, ticketValue, commentsValue);

        dashboardPage.clickOnSelectedActivity(activityList.get(0), activityList.get(1));
        dashboardPage.validateActivity(projectOptionValue, categoryOptionValue, hourValue, ticketValue, commentsValue);
        dashboardPage.closeActivityModal();

        // Update of activity created

        /*
        ToDO: Update addActivity() method to fillForm() and make it more generic removing the snackbar validation
         */

        dashboardPage.clickOnSelectedActivity(activityList.get(0), activityList.get(1));
        List<String> updatedAcicityList = dashboardPage.addActivity("iTexico - Delivery", "Support to Operations", "7", "Selenium", "Updating ticket created");
        dashboardPage.clickOnSelectedActivity(updatedAcicityList.get(0), updatedAcicityList.get(1));
        dashboardPage.validateActivity("iTexico - Delivery", "Support to Operations", "7", "Selenium", "Updating ticket created");
        dashboardPage.closeActivityModal();
    }

    @Test(groups = {"Calendar_1"})
    public void getColsSizeOfCalendar(Method method) {
        ExtentTestManager.startTest(method.getName(), "Getting the cols size of calendar");

        startLogin();
        assertTrue(dashboardPage.isDisplayed());
        assertEquals(dashboardPage.getCalendarColsSize(), 7);
    }

    @Test(groups = {"Calendar_2"})
    public void getDaysNameOfCalendar(Method method) {
        ExtentTestManager.startTest(method.getName(), "Getting the days name of week");

        List<String> expectedLabelsList = ImmutableList.of("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");

        startLogin();
        assertTrue(dashboardPage.isDisplayed());
        assertEquals(dashboardPage.getDaysOfTheWeek(), expectedLabelsList);
    }

    @Test(groups = {"Calendar_3"})
    public void getCalendarMonthView(Method method) {
        ExtentTestManager.startTest(method.getName(), "Getting the days of the month");

        startLogin();
        assertTrue(dashboardPage.isDisplayed());
        assertEquals(dashboardPage.calendarMonthView(), LocalDate.now().lengthOfMonth());
    }


}
