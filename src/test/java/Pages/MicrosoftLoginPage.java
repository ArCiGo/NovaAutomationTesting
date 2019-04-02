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

    public MicrosoftLoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 2);
    }

    // Elements

    @FindBy(name = "loginfmt")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='idSIButton9']")
    private  WebElement nextButton;

    @FindBy(id = "i0118")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='idSIButton9']")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id='idBtn_Back']")
    private WebElement noButton;

    // Methods

    public boolean isLoaded() {
        boolean isLoaded = driver.getCurrentUrl().contains("microsoftonline")
                ? true : false;

        return isLoaded;
    }

    public void fillForm() throws Exception {
        emailInput.clear();
        emailInput.sendKeys("armando.cifuentes@itexico.net");
        nextButton.click();

        wait.until(ExpectedConditions.visibilityOf(passwordInput));

        passwordInput.clear();
        passwordInput.sendKeys("Intheend12");
        signInButton.click();
        noButton.click();
    }
}
