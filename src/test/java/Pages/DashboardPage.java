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

    @FindBy(xpath = "//body[@class='dimmable dimmed']/div[@class='ui inverted page modals dimmer transition visible active']/div[@class='ui large modal transition visible active fade up visible transition sc-cSHVUG fYdhRt']/div[@class='sc-chPdSV Ztmir content']/div[@class='description']/div/div[@class='sc-cLQEGU ccJTjf']/div[1]/div[1]")
    private WebElement projectCombobox;

    @FindBy(xpath = "//div[@class='menu transition visible']//div[2]")
    private WebElement iTexicoTalentManagementeProjectOption;

    @FindBy(xpath = "//body[@class='dimmable dimmed']/div[@class='ui inverted page modals dimmer transition visible active']/div[@class='ui large modal transition visible active fade up visible transition sc-cSHVUG fYdhRt']/div[@class='sc-chPdSV Ztmir content']/div[@class='description']/div/div[@class='sc-cLQEGU ccJTjf']/div[2]/div[1]")
    private WebElement categoriesCombobox;

    @FindBy(xpath = "//div[13]")
    private WebElement trainingCategoryOption;

    @FindBy(xpath = "//input[@placeholder='Enter hours']")
    private WebElement hoursInput;

    @FindBy(xpath = "//input[@placeholder='Ticket Name']")
    private WebElement ticketInput;

    @FindBy(xpath = "//textarea[@title='*Comments']")
    private WebElement commentsTextarea;

    @FindBy(xpath = "//button[@class='sc-htoDjs biETra']")
    private WebElement createButton;

    @FindBy(xpath = "//span[@id='client-snackbar']")
    private WebElement successfulSnackbar;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/button[1]")
    private WebElement monthButton;

    @FindBy(xpath = "//div[@class='ui seven column grid day-grid-container sc-kjoXOD Wovug']")
    private WebElement gridMonthView;

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

    public void calendarMonthView() {
        //wait.until(ExpectedConditions.visibilityOf(monthButton));

        monthButton.click();

        String aux = gridMonthView.getAttribute("gridmode");
        System.out.println(aux);
    }

    public void addActivity() {
        addButton.click();

        wait.until(ExpectedConditions.visibilityOf(activitiesModal));
        assertTrue(activitiesModal.isDisplayed());

        projectCombobox.click();
        wait.until(ExpectedConditions.visibilityOf(iTexicoTalentManagementeProjectOption));
        iTexicoTalentManagementeProjectOption.click();

        categoriesCombobox.click();
        wait.until(ExpectedConditions.visibilityOf(trainingCategoryOption));
        trainingCategoryOption.click();

        hoursInput.clear();
        hoursInput.sendKeys("8");

        ticketInput.clear();
        ticketInput.sendKeys("Testing");

        commentsTextarea.clear();
        commentsTextarea.sendKeys("Something amazing");

        createButton.click();

        wait.until(ExpectedConditions.visibilityOf(successfulSnackbar));
        assertEquals(successfulSnackbar.getText(), "Activity successfully created");
    }
}
