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
    //кнопка "Конструктор"
    private final By constructorTab = By.linkText("Конструктор");
    //кнопка "Войти в аккаунт"
    private final By signInButton = By.xpath(".//button[text()='Войти в аккаунт']");

    /* POM методы */
    public void open() {
        driver.get(url);
    }
    public void clickUserProfile() {
        driver.findElement(profileButton).click();
    }

    public void goToLoginPage() {
        driver.findElement(signInButton).click();
    }

    public void goToBurgerConstructor() {
        driver.findElement(constructorTab).click();
    }


    /* POM чеки */
    public boolean isCurrentPositionMainPageAuthorized(){
        try {
            driver.findElement(makeOrderButton);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
    public boolean isCurrentPositionMainPageNotAuthorized(){
        try {
            driver.findElement(signInButton);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }





}
