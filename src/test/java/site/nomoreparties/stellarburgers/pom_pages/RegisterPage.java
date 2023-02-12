package site.nomoreparties.stellarburgers.pom_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import site.nomoreparties.stellarburgers.model.UserData;

/**
 * Описание страницы регистрации пользователя
 */

public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    /* POM локаторы */
    //Заголовок "Регистрация"
    private final By headerRegistration = By.linkText("Регистрация");
    //Поле Имя
    private final By nameInput = RelativeLocator.with(By.xpath("//input[contains(@class, 'input__textfield')]")).near(By.xpath(".//label[text()='Имя']"));
    //Поле email
     private final By emailInput = RelativeLocator.with(By.xpath("//input[contains(@class, 'input__textfield')]")).above(By.xpath(".//input[@name='Пароль']"));
    //Поле пароль
    private final By passwordInput = By.xpath(".//input[@name='Пароль']");
    //Кнопка "Зарегистрироваться"
    private final By signInButton = By.xpath(".//button[text()='Зарегистрироваться']");

    //Ссылка "Войти"
    private final By loginLink = By.xpath("//*[starts-with(@href, '/login')]");


    /* POM методы */
    public RegisterPage goToLoginPage() {
        driver.findElement(loginLink).click();
        return this;
    }

    public RegisterPage fillSignInForm(UserData userData) {
        driver.findElement(nameInput).click();
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(userData.getName());
        driver.findElement(emailInput).click();
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(userData.getEmail());
        driver.findElement(passwordInput).click();
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(userData.getPassword());
        return this;

    }

    public RegisterPage submitSignIn() {
        driver.findElement(signInButton).click();
        return this;
    }

    /* POM чеки */
}
