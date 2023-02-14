package site.nomoreparties.stellarburgers.pom_pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

/**
 * Описание страницы регистрации пользователя
 */

public class RegisterPage {
    /* POM локаторы */
    //Заголовок "Регистрация"
    private final By registrationHeader = By.xpath(".//h2[text()='Регистрация']");
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
    //Сообщениеоб ошибке
    private final By errorInputMessage = By.xpath(".//*[contains(@class, 'input__error')]");
    private final WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    /* POM методы */
    @Step("Перейти на страницу авторизации")
    public void goToLoginPage() {
        driver.findElement(loginLink).click();
    }

    @Step("Заполнить форму регистрации пользователя")
    public void fillSignInForm(String name, String email, String password) {
        driver.findElement(nameInput).click();
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).click();
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).click();
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Получить сообщение об ошибке")
    public String getErrorMessage() {
        return driver.findElement(errorInputMessage).getText();
    }

    @Step("Подтвердить создание пользователя")
    public void submitSignIn() {
        driver.findElement(signInButton).click();
    }


    /* POM чеки */
    @Step("Проверка. Находится на странице регистрации пользователя")
    public boolean isCurrentPositionRegisterPage() {
        try {
            driver.findElement(registrationHeader);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }
}
