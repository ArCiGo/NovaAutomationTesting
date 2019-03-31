package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MicrosoftLoginPage extends BaseApplicationPage {

    public MicrosoftLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    String Green = "\u001B[32m";
    String Defauld = "\u001B[0m";

    // Elements

    @FindBy(name = "loginfmt")
    static WebElement emailInput;

    @FindBy(xpath = "//input[@id='idSIButton9']")
    static  WebElement nextButton;

    @FindBy(id = "i0118")
    static WebElement passwordInput;

    @FindBy(xpath = "//input[@id='idSIButton9']")
    static WebElement signInButton;

    @FindBy(xpath = "//input[@id='idBtn_Back']")
    static WebElement noButton;

    // Methods

    public boolean isLoaded() {
        boolean isLoaded = driver.getCurrentUrl().contains("microsoftonline")
                ? true : false;

        return isLoaded;
    }

    public void fillForm() throws Exception {
        emailInput.clear();
        emailInput.sendKeys("");
        nextButton.click();
        Thread.sleep(1000);
        passwordInput.clear();
        passwordInput.sendKeys("");
        signInButton.click();
        noButton.click();
    }
}
