package site.nomoreparties.stellarburgers.pom_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Описание главной страницы приложения
 */

public class MainPage {
    private String url = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    /* POM локаторы */
    //кнопка "Личный кабинет"
    private final By profileButton = By.linkText("Личный Кабинет");
    //кнопка "Оформить заказ" - ожидается для залоггининых пользователей
    private final By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    //Заголовок вкладки "Конструктор"
    private final By makeBurgerHeader = By.xpath(".//button[text()='Оформить заказ']");
    //кнопка "Войти в аккаунт"
    private final By signInButton = By.xpath(".//button[text()='Войти в аккаунт']");

    /* POM методы */
    public MainPage open() {
        driver.get(url);
        return this;
    }
    public MainPage clickUserProfile() {
        driver.findElement(profileButton).click();
        return this;
    }

    public MainPage goToLoginPage() {
        driver.findElement(signInButton).click();
        return this;
    }


    /* POM чеки */
    public boolean isCurrentPositionMainPageWhenLoggedIn(){
        try {
            driver.findElement(makeOrderButton);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }





}
