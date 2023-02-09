package site.nomoreparties.stellarburgers.helpers;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BrowserRules extends ExternalResource {

    public static final String CHROME = "chrome";
    public static final String FIRE_FOX = "ff";

    private WebDriver driver;
    private String browser;

    public BrowserRules(String browser) {
        this.browser = browser;
    }

    public WebDriver getDriver() {
        return driver;
    }


    @Override
    protected void before() throws Throwable {
        if(CHROME.equals(browser)) {
            driver = new ChromeDriver();
        } else if (FIRE_FOX.equals(browser)) {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

    }

    @Override
    protected void after() {
        driver.quit();
    }
}
