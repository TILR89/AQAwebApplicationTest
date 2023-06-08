import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class testComponents {    public WebDriver driver;
    public HomePage homePage;

    public WebDriver initializeDriver() throws IOException {
        //properties class
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("src//main//resources//GlobalData.properties");
        properties.load(file);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");


        if (browserName.contains("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            if (browserName.contains("headless")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                driver = new FirefoxDriver(options);
            }

            driver = new FirefoxDriver();
            //driver.manage().window().setSize(new Dimension(1600, 900));
        } else if (browserName.equalsIgnoreCase("chrome")) {
            //WebDriverManager.chromedriver().setup();
            //driver = new ChromeDriver();
            System.setProperty("chromedriver.exe", "C:\\Selenium");
            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("msedgedriver.exe", "C://Selenium");
            //System.setProperty("webdriver.edge.driver", "msedgedriver.exe", "C://Selenium");
            //WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));

        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);

        //Convert String toHashmap (Jackson Databind)
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";


    }

    @BeforeMethod(alwaysRun = true)
    public HomePage launchApplication() throws IOException {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        homePage.openWebPage();
        return homePage;
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.close();
    }
}
