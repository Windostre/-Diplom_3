package site.nomoreparties.stellarburgers.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginTests {
    private WebDriver driver;
    private String email = "some_client@mail.com";
    private String password = "123456";


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }

    @Test
    public void loginViaPersonalPageSuccessWhenValidCredentialsProvided() {
        driver.findElement(By.linkText("Личный Кабинет")).click();
        driver.findElement(By.xpath(".//input[@name='name']")).sendKeys(email);
        driver.findElement(By.xpath(".//input[@name='Пароль']")).sendKeys(password);
        driver.findElement(By.xpath(".//button[text()='Войти']")).click();

        assertTrue(driver.findElement(By.xpath(".//button[text()='Оформить заказ']")).isDisplayed());

    }

}
