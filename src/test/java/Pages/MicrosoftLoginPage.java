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
    private String email = "armando.cifuentes@itexico.com";
    private String password = "";

    public MicrosoftLoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 5);
    }

    // Elements

    @FindBy(name = "loginfmt")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='idSIButton9']")
    private  WebElement nextButton;

    @FindBy(id = "i0118")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='idSIButton9' and contains(@value, \"Sign in\")] | //input[@id='idSIButton9' and contains(@value, \"Iniciar sesión\")]")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id='idBtn_Back']")
    private WebElement noButton;

    // Methods

    public boolean isLoaded() {
        wait.until(ExpectedConditions.visibilityOf(nextButton));

        try {
            return nextButton.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public void fillForm() {
        emailInput.clear();
        emailInput.sendKeys(email);
        nextButton.click();

        wait.until(ExpectedConditions.visibilityOf(signInButton));

        passwordInput.clear();
        passwordInput.sendKeys(password);
        signInButton.click();

        wait.until(ExpectedConditions.visibilityOf(noButton));

        noButton.click();
    }
}
