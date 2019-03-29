package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage extends BaseApplicationPage {

    public ContactUsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "center_column")
    static WebElement centerColumn;

    public boolean isLoaded(){
        try{
            return centerColumn.isDisplayed();
        }catch (Exception ex){
            return false;
        }
    }

    public void goTo(){
        driver.navigate().to("http://automationpractice.com/index.php?controller=contact");
    }
}
