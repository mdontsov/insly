package example;

import com.google.inject.Inject;
import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.HomePage;

import static org.junit.Assert.*;

public class Insly {

    static String domainUrl;

    @Inject
    private HomePage homePage;

    @Before("@start")
    public void setUp() {
        homePage = new HomePage();
    }

    @Given("^homepage is opened$")
    public void homepageIsOpened() {
        homePage.openUrl("https://signup.insly.com/signup");
        assertTrue(homePage.signUpFormBody.isDisplayed());
    }

    @And("^required elements are present$")
    public void requiredElementsArePresent() {
        assertTrue(homePage.brokerAdminEmail.isDisplayed());
        assertTrue(homePage.brokerAdminName.isDisplayed());
        assertTrue(homePage.brokerPersonPassword.isDisplayed());
        assertTrue(homePage.brokerPersonRepeatPassword.isDisplayed());
        assertTrue(homePage.brokerAdminPhone.isDisplayed());
        assertTrue(homePage.agreeCheckBoxes.get(0).isDisplayed());
        assertTrue(homePage.agreeCheckBoxes.get(1).isDisplayed());
        assertTrue(homePage.agreeCheckBoxes.get(2).isDisplayed());
    }

    @Then("^required fields are filled in$")
    public void requiredFieldsAreFilledIn() {
        domainUrl = homePage.randomWord();
        homePage.input(homePage.brokerName, domainUrl);
        homePage.selectValue(homePage.addressCountry, "United Kingdom");
        homePage.selectValue(homePage.companyProfile, "Insurance Company");
        homePage.selectValue(homePage.companyNumberOfEmployees, "11-100");
        homePage.selectValue(homePage.companyPersonDescription, "I am a tech guy");
    }

    @Then("^administrator account details are filled in$")
    public void administratorAccountDetailsAreFilledIn() {
        homePage.input(homePage.brokerAdminEmail, "admin@" + domainUrl + ".com");
        homePage.input(homePage.brokerAdminName, "Test User");
        homePage.click(homePage.passwordGeneratorLink);
        homePage.fluentWait().until(ExpectedConditions.visibilityOf(homePage.inslyAlertDialog));
        String password = homePage.getValue(homePage.generatedPassword);
        homePage.click(homePage.alertDialogOKButton);
        homePage.input(homePage.brokerPersonPassword, password);
        homePage.input(homePage.brokerPersonRepeatPassword, password);
        homePage.input(homePage.brokerAdminPhone, "123456789");
    }

    @Then("^terms and conditions are checked$")
    public void termsAndConditionsAreChecked() {
        homePage.click(homePage.agreeCheckBoxes.get(0));
        homePage.click(homePage.agreeCheckBoxes.get(1));
        homePage.click(homePage.agreeCheckBoxes.get(2));
        homePage.click(homePage.termsAndConditionsLink);
        homePage.fluentWait().until(ExpectedConditions.visibilityOf(homePage.agreeToTermsAndConditionsButton));
        homePage.click(homePage.agreeToTermsAndConditionsButton);
        homePage.click(homePage.privacyPolicyLink);
        homePage.fluentWait().until(ExpectedConditions.visibilityOf(homePage.privacyPolicyDialog));
        homePage.scrollToElement(homePage.lastParagraph.get(1));
        homePage.click(homePage.privacyPolicyDialogCloseButton.get(1));
    }

    @And("^signup is completed$")
    public void signupIsCompleted() throws InterruptedException {
        homePage.click(homePage.signUpButton);
        Thread.sleep(15000);
        assertTrue(homePage.getCurrentUrl().contains(domainUrl));
    }

    @After("@end")
    public void tearDown() {
        homePage.quit();
    }
}
