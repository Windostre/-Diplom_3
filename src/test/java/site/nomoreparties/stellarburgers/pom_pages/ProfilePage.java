package site.nomoreparties.stellarburgers.pom_pages;

import io.restassured.http.ContentType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;

/**
 * Описание страницы личного кабинет пользователя (доступно только для авторизованнх пользователей)
 */

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    /* POM локаторы */
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By profileButton = By.xpath(".//a[contains(@href, 'profile')]");

    /* POM методы */
    public void deleteUserViaApi(String accessToken) {
        given().contentType(ContentType.JSON)
                .baseUri("https://stellarburgers.nomoreparties.site")
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }

    public void logout() {
        driver.findElement(exitButton).click();
    }

    /* POM чеки */
    public boolean isCurrentPositionProfilePage() {
        try {
            driver.findElement(profileButton);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }


}
