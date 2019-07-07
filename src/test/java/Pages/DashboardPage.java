package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Log;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DashboardPage extends BaseApplicationPage {

    private WebDriverWait wait = new WebDriverWait(driver, 5);

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Elements

    @FindBy(xpath = "//div[@class='sc-kEYyzF dtAQRx']//img[@class='ui image']")
    private WebElement iTexicoLogoXPathLocator;

    @FindBy(xpath = "//button[@class='sc-Rmtcm ksuuFe']")
    private WebElement todayButtonXPathLocator;

    @FindBy(xpath = "//div[@class='row sc-dfVpRl iTthma']/div")
    private List<WebElement> calendarColsXPathLocator;

    @FindBy(xpath = "//span[@class='sc-kTUwUJ MUBma']")
    private List<WebElement> calendarDaysOfWeekXPathLocator;

    @FindBy(xpath = "//button[@title='shift+n']")
    private WebElement addButtonXPathLocator;

    @FindBy(xpath = "//div[@class='ui large modal transition visible active fade up visible transition sc-cSHVUG fYdhRt']")
    private WebElement activitiesModalXPathLocator;

    @FindBy(xpath = "//p[contains(text(), 'Project')]/following-sibling::div")
    private WebElement projectComboboxXPathLocator;

    private String projectOptionsXPathLocator = "//p[contains(text(), 'Project')]/following-sibling::div/div[@role='listbox']/div/span[contains(text(), 'iTexico')]";

    @FindBy(xpath = "//p[contains(text(), 'Categories')]/following-sibling::div")
    private WebElement categoriesComboboxXPathLocator;

    private String categoryOptionsXPathLocator = "//p[contains(text(), 'Categories')]/following-sibling::div/div[@role='listbox']/div/span[contains(text(), 'Training')]";

    private String hoursInputXPathLocator = "//p[contains(text(), 'Hours')]/following-sibling::div/input[@placeholder='Enter hours']";

    private String ticketInputXPathLocator = "//p[contains(text(), 'Ticket')]/following-sibling::div/input[@placeholder='Ticket Name']";

    private String commentsTextareaXPathLocator = "//p[contains(text(), '*Comments')]/following-sibling::textarea[@title='*Comments']";

    private String todayXPathLocator = "//div[contains(@class, 'today')]";

    @FindBy(xpath = "//div[contains(@class, 'description')]/descendant::button[contains(@label, 'save')]")
    private WebElement createButtonXPathLocator;

    @FindBy(xpath = "//div[contains(@class, 'description')]/descendant::button[contains(text(), 'cancel')]")
    private WebElement cancelButtonXPathLocator;

    @FindBy(xpath = "//span[@id='client-snackbar']")
    private WebElement successfulSnackbarXPathLocator;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/button[1]")
    private WebElement monthButtonXPathLocator;

    @FindBy(xpath = "//div[@class='ui seven column grid day-grid-container sc-kjoXOD Wovug']")
    private WebElement gridMonthViewXPathLocator;

    @FindBy(xpath = "//span[@class='sc-ksYbfQ gLpyUQ']")
    private List<WebElement> gridCellsXPathLocator;

    @FindBy(xpath = "//div[contains(@class,'ui seven column grid') and not(@gridmode)]/descendant::div[contains(@class,'column')]")
    private List<WebElement> calendarHeaderXPathLocator;

    @FindBy(xpath = "//div[contains(@class,'ui seven column grid') and @gridmode]/descendant::div[contains(@class,'column')]")
    private List<WebElement> calendarBodyXPathLocator;

    // Methods

    public boolean isDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(iTexicoLogoXPathLocator));

        try {
            return iTexicoLogoXPathLocator.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isTodayButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(todayButtonXPathLocator));

        try {
            return todayButtonXPathLocator.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public int getCalendarColsSize() {
        return calendarColsXPathLocator.size();
    }

    public List<String> getDaysOfTheWeek() {
        List<String> daysOfWeekList = new ArrayList<>();

        //wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//span[@class='sc-kTUwUJ MUBma']"), 7));

        for (WebElement item : calendarDaysOfWeekXPathLocator) {
            daysOfWeekList.add(item.getText());
        }

        return daysOfWeekList;
    }

    public int calendarMonthView() {
        //wait.until(ExpectedConditions.visibilityOf(monthButton));
        monthButtonXPathLocator.click();

        assertEquals(gridMonthViewXPathLocator.getAttribute("gridmode"), "MONTH");

        return gridCellsXPathLocator.size();
    }

    public void openActivityModal() {
        addButtonXPathLocator.click();

        wait.until(ExpectedConditions.visibilityOf(activitiesModalXPathLocator));
        assertTrue(activitiesModalXPathLocator.isDisplayed());
    }

    public void closeActivityModal() {
        cancelButtonXPathLocator.click();
    }

    public List<String> fillActivity(String projectOptionValue, String categoryOptionValue, String hourValue, String ticketValue, String commentsValue) {
        List<String> activityValues = new ArrayList<String>();
        WebElement today = getTodayElement();

        activityValues.add(today.getText());
        activityValues.add(ticketValue);

        projectComboboxXPathLocator.click();
        WebElement projectOption = getProjectOptionElement(projectOptionValue);
        wait.until(ExpectedConditions.visibilityOf(projectOption));
        projectOption.click();

        categoriesComboboxXPathLocator.click();
        WebElement categoryOption = getCategoryOptionElement(categoryOptionValue);
        wait.until(ExpectedConditions.visibilityOf(categoryOption));
        categoryOption.click();

        WebElement hourInput = getHourElement();
        hourInput.clear();
        hourInput.sendKeys(hourValue);

        WebElement ticketInput = getTicketElement();
        ticketInput.clear();
        ticketInput.sendKeys(ticketValue);

        WebElement commentsTextarea = getCommentsElement();
        commentsTextarea.clear();
        commentsTextarea.sendKeys(commentsValue);

        createButtonXPathLocator.click();

        wait.until(ExpectedConditions.visibilityOf(successfulSnackbarXPathLocator));

        if (successfulSnackbarXPathLocator.getText().contains("created")) {
            assertEquals(successfulSnackbarXPathLocator.getText(), "Activity successfully created");
        } else {
            assertEquals(successfulSnackbarXPathLocator.getText(), "Activity successfully updated");
        }

        return activityValues;
    }

    public WebElement getSelectedActivity(String today, String ticketValue) {
        String day;
        int indexDateHeader = 0, indexTicketValue = 0;
        WebElement todaySelectedActivity;

        for (int i = 0; i < calendarHeaderXPathLocator.size(); i++) {
            day = calendarHeaderXPathLocator.get(i).findElement(By.xpath("./div/div")).getText();

            if (day.equals(today)) {
                indexDateHeader = i;
            }
        }

        List<WebElement> todayColumnActivities = calendarBodyXPathLocator.get(indexDateHeader).findElements(By.xpath("./div/div"));

        for (int j = 0; j < todayColumnActivities.size(); j++) {
            if (todayColumnActivities.get(j).findElement(By.xpath("./div/p/following-sibling::span[1]")).getText().equals(ticketValue)) {
                indexTicketValue = j;
            }
        }

        return todaySelectedActivity = todayColumnActivities.get(indexTicketValue);
    }

    public void clickOnSelectedActivity(String today, String ticketValue) {
        WebElement todaySelectedActivity = getSelectedActivity(today, ticketValue);

        todaySelectedActivity.click();
    }

    public void clickOnSelectedActivityToDelete(String today, String ticketValue) throws InterruptedException {
        WebElement todaySelectedActivity =getSelectedActivity(today, ticketValue).findElement(By.xpath("./descendant::div[contains(@class, 'dropdown')]"));
        todaySelectedActivity.click();

        try {
            todaySelectedActivity.findElement(By.xpath("./div[contains(@class, 'menu')]/div[2]")).click();

            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (UnhandledAlertException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.visibilityOf(successfulSnackbarXPathLocator));

        if (successfulSnackbarXPathLocator.getText().contains("deleted")) {
            assertEquals(successfulSnackbarXPathLocator.getText(), "Activity successfully deleted");
        }
    }

    public void validateActivity(String projectOptionValue, String categoryOptionValue, String hourValue, String ticketValue, String commentsValue) {
        assertEquals(projectComboboxXPathLocator.getText(), projectOptionValue);
        assertEquals(categoriesComboboxXPathLocator.getText(), categoryOptionValue);
        assertEquals(getHourElement().getAttribute("value"), hourValue);
        assertEquals(getTicketElement().getAttribute("value"), ticketValue);
        assertEquals(getCommentsElement().getText(), commentsValue);
    }

    // Setting dynamic elements that appears in modal

    private WebElement getProjectOptionElement(String textOption) {
        return driver.findElement(By.xpath(projectOptionsXPathLocator.replace("iTexico", textOption)));
    }

    private WebElement getCategoryOptionElement(String textOption) {
        return driver.findElement(By.xpath(categoryOptionsXPathLocator.replace("Training", textOption)));
    }

    private WebElement getHourElement() {
        return driver.findElement(By.xpath(hoursInputXPathLocator));
    }

    private WebElement getTicketElement() {
        return driver.findElement(By.xpath(ticketInputXPathLocator));
    }

    private WebElement getCommentsElement() {
        return driver.findElement(By.xpath(commentsTextareaXPathLocator));
    }

    private WebElement getTodayElement() {
        return driver.findElement(By.xpath(todayXPathLocator));
    }
}
