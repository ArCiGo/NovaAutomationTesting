package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BaseApplicationPage {

    public DashboardPage(WebDriver driver) {
        super(driver);
        //PageFactory.initElements(driver, this);
    }

    // Elements

    @FindBy(xpath = "//div[@class='sc-kEYyzF dtAQRx']//img[@class='ui image']")
    private WebElement iTexicoLogo;

    @FindBy(xpath = "//button[@class='sc-Rmtcm ksuuFe']")
    private WebElement todayButton;

    @FindBy(xpath = "//div[@class='row sc-dfVpRl iTthma']/div")
    private List<WebElement> calendarCols;

    @FindBy(xpath = "//span[@class='sc-kTUwUJ MUBma']")
    private List<WebElement> calendarDaysOfWeek;

    // Methods

    public boolean isDisplayed() {
        try {
            return iTexicoLogo.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isTodayButtonDisplayed() {
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

        for(WebElement item : calendarDaysOfWeek) {
            daysOfWeekList.add(item.getText());
        }

        return daysOfWeekList;
    }
}
