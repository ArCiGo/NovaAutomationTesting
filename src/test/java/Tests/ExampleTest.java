package Tests;

import Pages.ContactUsPage;
import Pages.HomePage;
import Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ExampleTest extends BaseTest {

    @Test
    public void TCID1() throws Exception {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.goTo();

        assertTrue(contactUsPage.isLoaded());
    }

    @Test
    public void TCID2(){
        HomePage homePage = new HomePage(driver);
        homePage.goTo();

        assertTrue(homePage.isLoaded());

        ContactUsPage contactUsPage = homePage.header.clickContactUsButton();
        assertTrue(contactUsPage.isLoaded(), "The Contact Us page didn't open");
    }
}
