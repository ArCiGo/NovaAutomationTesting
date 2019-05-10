package Tests;

import AutomationResources.BrowserType;
import AutomationResources.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import utilities.Log;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setupForEverySingleMethod() throws Exception {
        Log.info("I am in Before Method! Test is starting!");

        WebDriverFactory factory = new WebDriverFactory();

        driver = factory.create(BrowserType.Chrome);
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void cleanupAfterEveryTestMethod(){
        Log.info("I am in After Method! Test is ending!");

        driver.close();
        driver.quit();
    }
}
