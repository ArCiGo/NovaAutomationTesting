package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DashboardPage extends BaseApplicationPage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    private WebDriverWait wait = new WebDriverWait(driver, 5);

    // Elements

    @FindBy(xpath = "//div[@class='sc-kEYyzF dtAQRx']//img[@class='ui image']")
    private WebElement iTexicoLogo;

    @FindBy(xpath = "//button[@class='sc-Rmtcm ksuuFe']")
    private WebElement todayButton;

    @FindBy(xpath = "//div[@class='row sc-dfVpRl iTthma']/div")
    private List<WebElement> calendarCols;

    @FindBy(xpath = "//span[@class='sc-kTUwUJ MUBma']")
    private List<WebElement> calendarDaysOfWeek;

    @FindBy(xpath = "//button[@title='shift+n']")
    private WebElement addButton;

    @FindBy(xpath = "//div[@class='ui large modal transition visible active fade up visible transition sc-cSHVUG fYdhRt']")
    private WebElement activitiesModal;

    @FindBy(xpath = "//p[contains(text(), 'Project')]/following-sibling::div")
    private WebElement projectCombobox;

    private String projectOptionsXPath = "//p[contains(text(), 'Project')]/following-sibling::div/div[@role='listbox']/div/span[contains(text(), 'iTexico')]";

    @FindBy(xpath = "//p[contains(text(), 'Categories')]/following-sibling::div")
    private WebElement categoriesCombobox;

    private String categoryOptionsXpath = "//p[contains(text(), 'Categories')]/following-sibling::div/div[@role='listbox']/div/span[contains(text(), 'Training')]";

    private String hoursInput = "//p[contains(text(), 'Hours')]/following-sibling::div/input[@placeholder='Enter hours']";

    private String ticketInput = "//p[contains(text(), 'Ticket')]/following-sibling::div/input[@placeholder='Ticket Name']";

    private String commentsTextarea = "//p[contains(text(), '*Comments')]/following-sibling::textarea[@title='*Comments']";

    @FindBy(xpath = "//div[contains(@class, 'description')]/descendant::button[contains(@label, 'save')]")
    private WebElement createButton;

    @FindBy(xpath = "//span[@id='client-snackbar']")
    private WebElement successfulSnackbar;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/button[1]")
    private WebElement monthButton;

    @FindBy(xpath = "//div[@class='ui seven column grid day-grid-container sc-kjoXOD Wovug']")
    private WebElement gridMonthView;

    @FindBy(xpath = "//span[@class='sc-ksYbfQ gLpyUQ']")
    private List<WebElement> gridCells;

    // Methods

    public boolean isDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(iTexicoLogo));

        try {
            return iTexicoLogo.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isTodayButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(todayButton));

        try {
            return todayButton.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public int getCalendarColsSize() {
        return calendarCols.size();
    }

    public List<String> getDaysOfTheWeek() {
        List<String> daysOfWeekList = new ArrayList<>();

        //wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//span[@class='sc-kTUwUJ MUBma']"), 7));

        for(WebElement item : calendarDaysOfWeek) {
            daysOfWeekList.add(item.getText());
        }

        return daysOfWeekList;
    }

    public int calendarMonthView() {
        //wait.until(ExpectedConditions.visibilityOf(monthButton));
        monthButton.click();

        assertEquals(gridMonthView.getAttribute("gridmode"), "MONTH");

        return gridCells.size();
    }

    public void addActivity() {
        addButton.click();

        wait.until(ExpectedConditions.visibilityOf(activitiesModal));
        assertTrue(activitiesModal.isDisplayed());

        projectCombobox.click();
        WebElement projectOption = getProjectOptionElement("iTexico - Talent Management");
        wait.until(ExpectedConditions.visibilityOf(projectOption));
        projectOption.click();

        categoriesCombobox.click();
        WebElement categoryOption = getCategoryOptionElement("Training & Development (not project related)");
        wait.until(ExpectedConditions.visibilityOf(categoryOption));
        categoryOption.click();

        WebElement hourInput = getHourElement();
        hourInput.clear();
        hourInput.sendKeys("8");

        WebElement ticketInput = getTicketElement();
        ticketInput.clear();
        ticketInput.sendKeys("Testing");

        WebElement commentsTextarea = getCommentsElement();
        commentsTextarea.clear();
        commentsTextarea.sendKeys("Something amazing");

        createButton.click();

        wait.until(ExpectedConditions.visibilityOf(successfulSnackbar));
        assertEquals(successfulSnackbar.getText(), "Activity successfully created");
    }

    private WebElement getProjectOptionElement(String textOption) {
        return driver.findElement(By.xpath(projectOptionsXPath.replace("iTexico", textOption)));
    }

    private WebElement getCategoryOptionElement(String textOption) {
        return driver.findElement(By.xpath(categoryOptionsXpath.replace("Training", textOption)));
    }

    private WebElement getHourElement() {
        return driver.findElement(By.xpath(hoursInput));
    }

    private WebElement getTicketElement() {
        return driver.findElement(By.xpath(ticketInput));
    }

    private WebElement getCommentsElement() {
        return driver.findElement(By.xpath(commentsTextarea));
    }
}
