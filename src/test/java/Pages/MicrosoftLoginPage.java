package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

public class MicrosoftLoginPage extends BaseApplicationPage {

    private WebDriverWait wait;
    private String email = "";
    private String password = "";

    public MicrosoftLoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 5);
    }

    // Elements

    @FindBy(name = "loginfmt")
    private WebElement emailInputNameLocator;

    @FindBy(xpath = "//input[@id='idSIButton9']")
    private WebElement nextButtonXPathLocator;

    @FindBy(id = "i0118")
    private WebElement passwordInputIdLocator;

    @FindBy(xpath = "//input[@id='idSIButton9' and contains(@value, \"Sign in\")] | //input[@id='idSIButton9' and contains(@value, \"Iniciar sesión\")]")
    private WebElement signInButtonXPathLocator;

    @FindBy(xpath = "//input[@id='idBtn_Back']")
    private WebElement noButtonXPathLocator;

    // Methods

    public boolean isLoaded() {
        wait.until(ExpectedConditions.visibilityOf(nextButtonXPathLocator));

        try {
            return nextButtonXPathLocator.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public void fillForm() {
        emailInputNameLocator.clear();
        emailInputNameLocator.sendKeys(email);
        nextButtonXPathLocator.click();

        wait.until(ExpectedConditions.visibilityOf(signInButtonXPathLocator));

        passwordInputIdLocator.clear();
        passwordInputIdLocator.sendKeys(password);
        signInButtonXPathLocator.click();

        wait.until(ExpectedConditions.visibilityOf(noButtonXPathLocator));

        noButtonXPathLocator.click();
    }
}
