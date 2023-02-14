package site.nomoreparties.stellarburgers.helpers;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BrowserRules extends ExternalResource {

    public static final String CHROME = "chrome";
    public static final String YANDEX = "yandex";
    private WebDriver driver;
    private final String browser;

    public BrowserRules(String browser) {
        this.browser = browser;
    }

    public WebDriver getDriver() {
        return driver;
    }


    @Override
    protected void before() throws Throwable {
        if (CHROME.equals(browser)) {
            driver = new ChromeDriver();
        } else if (YANDEX.equals(browser)) {
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\windo\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

    }

    @Override
    protected void after() {
        driver.quit();
    }
}
