package site.nomoreparties.stellarburgers.pom_pages;

import org.openqa.selenium.WebDriver;

/**
 * Описание страницы личного кабинет пользователя (доступно только для авторизованнх пользователей)
 */

public class UserPersonalPage {
    private WebDriver driver;

    public UserPersonalPage(WebDriver driver) {
        this.driver = driver;
    }
}
