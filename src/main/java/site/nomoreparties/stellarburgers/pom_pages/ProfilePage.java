package site.nomoreparties.stellarburgers.pom_pages;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;

/**
 * Описание страницы личного кабинет пользователя (доступно только для авторизованнх пользователей)
 */

public class ProfilePage {
    /* POM локаторы */
    //кнопка "Выход"
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    //кнопка "Профиль"
    private final By profileButton = By.xpath(".//a[contains(@href, 'profile')]");
    //Логотип
    private final By logo = By.xpath(".//div[contains(@class, 'header__logo')]");
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    /* POM методы */
    @Step("Удалить пользователя")
    public void deleteUserViaApi(String accessToken) {
        given().contentType(ContentType.JSON)
                .baseUri("https://stellarburgers.nomoreparties.site")
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }

    @Step("Выйти из приложения")
    public void logout() {
        driver.findElement(exitButton).click();
    }

    @Step("Перейти на главную страницу")
    public void goToMainPage() {
        driver.findElement(logo).click();
    }

    /* POM чеки */
    @Step("Проверка. Находится в личном кабинете")
    public boolean isCurrentPositionProfilePage() {
        try {
            driver.findElement(profileButton);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }


}
