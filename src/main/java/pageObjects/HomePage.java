package pageObjects;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage() {
        PageFactory.initElements(driver(), this);
    }

    @FindBy(css = ".panel-body")
    public WebElement signUpFormBody;

    @FindBy(css = "#broker_admin_email")
    public WebElement brokerAdminEmail;

    @FindBy(css = "#broker_admin_name")
    public WebElement brokerAdminName;

    @FindBy(css = "#broker_person_password")
    public WebElement brokerPersonPassword;

    @FindBy(css = "#broker_person_password_repeat")
    public WebElement brokerPersonRepeatPassword;

    @FindBy(css = "#broker_admin_phone")
    public WebElement brokerAdminPhone;

    @FindBy(css = ".icon-check-empty")
    public List<WebElement> agreeCheckBoxes;

    @FindBy(css = "#submit_save")
    public WebElement signUpButton;

    @FindBy(css = "#broker_name")
    public WebElement brokerName;

    @FindBy(css = "#broker_address_country")
    public WebElement addressCountry;

    @FindBy(css = "#prop_company_profile")
    public WebElement companyProfile;

    @FindBy(css = "#prop_company_no_employees")
    public WebElement companyNumberOfEmployees;

    @FindBy(css = "#prop_company_person_description")
    public WebElement companyPersonDescription;

    @FindBy(css = "[role='dialog']")
    public WebElement inslyAlertDialog;

    @FindBy(css = "#insly_alert>b")
    public WebElement generatedPassword;

    @FindBy(css = ".element>a")
    public WebElement passwordGeneratorLink;

    @FindBy(css = ".ui-dialog-buttonset>.primary")
    public WebElement alertDialogOKButton;

    @FindBy(linkText = "terms and conditions")
    public WebElement termsAndConditionsLink;

    @FindBy(linkText = "privacy policy")
    public WebElement privacyPolicyLink;

    @FindBy(css = ".ui-dialog-buttonset>.primary")
    public WebElement agreeToTermsAndConditionsButton;

    @FindBy(css = ".privacy-policy-dialog")
    public WebElement privacyPolicyDialog;

    @FindBy(css = ".privacy-policy-dialog>div>div")
    public List<WebElement> lastParagraph;

    @FindBy(css = "a[role='button']>span")
    public List<WebElement> privacyPolicyDialogCloseButton;
}
