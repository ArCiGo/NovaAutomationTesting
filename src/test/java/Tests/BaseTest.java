package Tests;

import AutomationResources.BrowserType;
import AutomationResources.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import utilities.Log;

public class BaseTest {

    public WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setupForEverySingleMethod() throws Exception {
        Log.startLog("I'm setupForEverySingleMethod() for every test");

        WebDriverFactory factory = new WebDriverFactory();

        driver = factory.create(BrowserType.Chrome);
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void cleanupAfterEveryTestMethod(){
        driver.close();
        driver.quit();

        Log.endLog("I'm cleanupAfterEveryTestMethod()");
    }
}
