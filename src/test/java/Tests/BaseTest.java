package Tests;

import AutomationResources.BrowserType;
import AutomationResources.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setupForEverySingleMethod() throws Exception {
        WebDriverFactory factory = new WebDriverFactory();

        driver = factory.create(BrowserType.Chrome);
        driver.manage().window().maximize();
    }

    @AfterTest(alwaysRun = true)
    public void cleanupAfterEveryTestMethod(){
        driver.close();
        driver.quit();
    }
}
