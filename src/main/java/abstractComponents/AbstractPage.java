package abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPage {
    @FindBy(xpath ="GlobalData.properties")
            private WebElement switchLanguageButton;

    @FindBy(xpath ="//li[contains(.,'Sign in')]")
    private WebElement signInDropdownMenu;

    @FindBy(xpath = "(//a[contains(.,'Start here')])[1]")
    private WebElement startHereNewAccountLink;

    @FindBy(xpath = "(//button[contains(text(),'Log in')])[1]")
            private WebElement loginButton;

    @FindBy(name = "emailaddress")
    private WebElement emailLoginField;

    @FindBy(name = "password")
    private WebElement passwordLoginField;

    @FindBy(xpath ="//button[contains(text(), 'Log in')]")
    private WebElement submitLoginButton;

    @FindBy(xpath = "//a//span[contains(text(),'Shopping cart')]")
            private WebElement shoppingCartButton;


    WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;

    }

    public void waitElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitElementToDisappear(WebElement element){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
