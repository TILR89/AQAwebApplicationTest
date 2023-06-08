package pageObjects;

import abstractComponents.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {



    WebDriver driver;
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void  openWebPage(){
        driver.get("https://www.coolblue.nl/en.");
    }
    public void login(String ){

    }
}
