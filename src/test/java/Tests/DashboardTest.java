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
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DashboardTest extends BaseTest {

    private DashboardPage dashboardPage;

    List<String> createActivityList = new ArrayList<String>() {{
        add("iTexico - Talent Management");
        add("Training & Development (not project related)");
        add("8");
        add("Testing something amazing");
        add("Something amazing");
    }};

    List<String> updateActivityList = new ArrayList<String>() {{
        add("iTexico - Delivery");
        add("Support to Operations");
        add("7");
        add("Selenium");
        add("Updating ticket created");
    }};

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
        List<String> activityList = dashboardPage.fillActivity(
                createActivityList.get(0),
                createActivityList.get(1),
                createActivityList.get(2),
                createActivityList.get(3),
                createActivityList.get(4)
        );

        dashboardPage.clickOnSelectedActivity(activityList.get(0), activityList.get(1));
        dashboardPage.validateActivity(
                createActivityList.get(0),
                createActivityList.get(1),
                createActivityList.get(2),
                createActivityList.get(3),
                createActivityList.get(4)
        );
        dashboardPage.closeActivityModal();
    }

    @Test(groups = {"Update activity"})
    public void updateActivity(Method method) {
        ExtentTestManager.startTest(method.getName(), "Update an activity");

        startLogin();
        assertTrue(dashboardPage.isDisplayed());

        // Creation of activity
        dashboardPage.openActivityModal();
        List<String> activityList = dashboardPage.fillActivity(
                createActivityList.get(0),
                createActivityList.get(1),
                createActivityList.get(2),
                createActivityList.get(3),
                createActivityList.get(4)
        );

        dashboardPage.clickOnSelectedActivity(activityList.get(0), activityList.get(1));
        dashboardPage.validateActivity(
                createActivityList.get(0),
                createActivityList.get(1),
                createActivityList.get(2),
                createActivityList.get(3),
                createActivityList.get(4)
        );
        dashboardPage.closeActivityModal();

        // Update of activity created
        dashboardPage.clickOnSelectedActivity(activityList.get(0), activityList.get(1));
        List<String> updatedActivityList = dashboardPage.fillActivity(
                updateActivityList.get(0),
                updateActivityList.get(1),
                updateActivityList.get(2),
                updateActivityList.get(3),
                updateActivityList.get(4)
        );
        dashboardPage.clickOnSelectedActivity(updatedActivityList.get(0), updatedActivityList.get(1));
        dashboardPage.validateActivity(
                updateActivityList.get(0),
                updateActivityList.get(1),
                updateActivityList.get(2),
                updateActivityList.get(3),
                updateActivityList.get(4)
        );
        dashboardPage.closeActivityModal();
    }

    @Test(groups = {"Delete activity"})
    public void deleteActivity(Method method) {
        ExtentTestManager.startTest(method.getName(), "Delete an activity");

        startLogin();
        assertTrue(dashboardPage.isDisplayed());

        // Creation of activity
        dashboardPage.openActivityModal();
        List<String> activityList = dashboardPage.fillActivity(
                createActivityList.get(0),
                createActivityList.get(1),
                createActivityList.get(2),
                createActivityList.get(3),
                createActivityList.get(4)
        );

        dashboardPage.clickOnSelectedActivity(activityList.get(0), activityList.get(1));
        dashboardPage.validateActivity(
                createActivityList.get(0),
                createActivityList.get(1),
                createActivityList.get(2),
                createActivityList.get(3),
                createActivityList.get(4)
        );
        dashboardPage.closeActivityModal();

        // Delete of activity created
        dashboardPage.clickOnSelectedActivityToDelete(activityList.get(0), activityList.get(1));
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
