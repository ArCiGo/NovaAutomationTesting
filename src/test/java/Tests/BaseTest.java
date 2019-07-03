package Tests;

import AutomationResources.BrowserType;
import AutomationResources.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utilities.Log;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeTest(alwaysRun = true)
    public void setupForEverySingleMethod() throws Exception {
        Log.info("I am in Before Method! Test is starting!");

        WebDriverFactory factory = new WebDriverFactory();

        driver = factory.create(BrowserType.Chrome);
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
    }

    @AfterTest(alwaysRun = true)
    public void cleanupAfterEveryTestMethod(){
        Log.info("I am in After Method! Test is ending!");

        driver.close();
        driver.quit();
    }
}