package site.nomoreparties.stellarburgers.pom_pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Описание главной страницы приложения
 */

public class MainPage {
    /* POM локаторы */
    //кнопка "Личный кабинет"
    private final By profileButton = By.linkText("Личный Кабинет");
    //кнопка "Оформить заказ" - ожидается для залоггининых пользователей
    private final By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    //кнопка "Конструктор"
    private final By constructorTab = By.linkText("Конструктор");
    //кнопка "Войти в аккаунт"
    private final By signInButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final String url = "https://stellarburgers.nomoreparties.site/";
    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    /* POM методы */
    public void open() {
        driver.get(url);
    }

    @Step("Нажать на кнопку Личный кабинет")
    public void clickUserProfile() {
        driver.findElement(profileButton).click();
    }

    @Step("Перейти на страницу авторизации")
    public void goToLoginPage() {
        driver.findElement(signInButton).click();
    }

    @Step("Перейти на страницу конструктора")
    public void goToBurgerConstructor() {
        driver.findElement(constructorTab).click();
    }

    /* POM чеки */
    @Step("Проверка. Авторизованный пользователь находится на главной страницы")
    public boolean isCurrentPositionMainPageAuthorized() {
        try {
            driver.findElement(makeOrderButton);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Step("Проверка. Неавторизованный пользователь находится на главной страницы")
    public boolean isCurrentPositionMainPageNotAuthorized() {
        try {
            driver.findElement(signInButton);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


}
