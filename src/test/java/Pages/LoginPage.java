package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class LoginPage extends BaseApplicationPage {

    // Constructor

    public LoginPage(WebDriver driver) {
        super(driver);
        //PageFactory.initElements(driver, this);
    }

    // Elements

    @FindBy(xpath = "//*[@class='sc-htpNat cTpQuI']")
    static WebElement loginForm;

    @FindBy(xpath = "//a[@href='https://login.microsoftonline.com/e436ac59-887c-458a-b2d1-7d6759a4ec75/oauth2/v2.0/authorize?client_id=9b47c987-ab9e-4b45-945e-b6af511579a0&response_type=code&redirect_uri=https://nova.itexico.com/authcode&response_mode=query&scope=openid%20user.read']")
    static WebElement loginButtonMicrosoft;

    // Methods

    public void goTo() {
        driver.navigate().to("https://nova.itexico.com/login");
    }

    public boolean isLoaded() {
        try {
            return loginForm.isDisplayed();
        } catch(Exception ex){
            return false;
        }
    }

    public void login() throws Exception {
        loginButtonMicrosoft.click();

        MicrosoftLoginPage microsoftLoginPage = new MicrosoftLoginPage(driver);
        assertTrue(microsoftLoginPage.isLoaded());
        microsoftLoginPage.fillForm();
    }
}
