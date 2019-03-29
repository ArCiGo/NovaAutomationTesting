package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderSection extends BaseApplicationPage {

    public HeaderSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "contact-link")
    public WebElement contactUsButton;

    @FindBy(className = "login")
    public WebElement signInButton;

    public ContactUsPage clickContactUsButton(){
        contactUsButton.click();

        return new ContactUsPage(driver);
    }
}
