package site.nomoreparties.stellarburgers.pom_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.model.UserData;

import java.time.Duration;

/**
 * Описание главной страницы авторизации пользователя
 */
public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    /* POM локаторы */
    //поле ввода e-mail
    private final By emailInput = By.xpath(".//input[@name='name']");
    //поле ввода пароля
    private final By passwordInput = By.xpath(".//input[@name='Пароль']");
    //кнопка "Войти"
    private final By enterButton = By.xpath(".//button[text()='Войти']");
    // Заголовок формы авторизации
    private final By loginHeader = By.xpath(".//h2[text()='Вход']");
    //Ссылка "Зарегистрироваться"
    private final By registerLink = By.xpath("//*[starts-with(@href, '/register')]");
    //Ссылка "Восстановить пароль"
    private final By restorePasswordLink = By.xpath("//*[starts-with(@href, '/forgot-password')]");


    /* POM методы */
    public LoginPage logIn(UserData userData) {
        driver.findElement(emailInput).click();
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(userData.getEmail());
        driver.findElement(passwordInput).click();
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(userData.getPassword());
        driver.findElement(enterButton).click();
        return this;

    }

    public LoginPage logInString(String email, String password) {
        driver.findElement(emailInput).click();
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).click();
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(enterButton).click();
        return this;

    }

    public LoginPage goToRegisterPage() {
        driver.findElement(registerLink).click();
        return this;
    }

    public LoginPage goToRestorePasswordPage() {
        driver.findElement(restorePasswordLink).click();
        return this;
    }


    public String getAccessToken() throws InterruptedException {
        Thread.sleep(500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(String.format("return window.localStorage.getItem('%s');", "accessToken"));
    }


    /* POM чеки */
    public boolean isCurrentPositionLoginPage() {
        try {
            driver.findElement(loginHeader);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }


}
