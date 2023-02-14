package site.nomoreparties.stellarburgers.pom_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Описание страницы восстановления пароля
 */
public class ForgotPasswordPage {
    /* POM локаторы */
    //Ссылка "Войти"
    private final By loginLink = By.xpath(".//*[starts-with(@href, '/login')]");
    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    /* POM методы */
    public void goToLoginPage() {
        driver.findElement(loginLink).click();
    }
}
