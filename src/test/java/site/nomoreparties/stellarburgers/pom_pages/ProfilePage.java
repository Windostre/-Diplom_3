package site.nomoreparties.stellarburgers.pom_pages;

import org.openqa.selenium.WebDriver;

/**
 * Описание страницы личного кабинет пользователя (доступно только для авторизованнх пользователей)
 */

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
}
