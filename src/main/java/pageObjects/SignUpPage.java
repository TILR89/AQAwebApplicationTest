package pageObjects;

import abstractComponents.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends AbstractPage {

    WebDriver driver;
    @FindBy(id = "emailaddress")
    private WebElement emailInputField;

    @FindBy(id = "password")
    private WebElement passwordInputField;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(id = "recaptcha-anchor")
    private WebElement recaptchaCheckbox;

    @FindBy(name = "completeRegistration")
    private WebElement registerButton;


    public SignUpPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
