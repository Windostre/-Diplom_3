package site.nomoreparties.stellarburgers.pom_pages;

import org.openqa.selenium.WebDriver;

/**
 * Описание страницы личного кабинет пользователя (доступно только для авторизованнх пользователей)
 */

public class UserAccountPage {
    private WebDriver driver;

    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
    }
}
