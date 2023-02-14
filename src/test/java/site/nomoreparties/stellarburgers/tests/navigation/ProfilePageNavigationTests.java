package site.nomoreparties.stellarburgers.tests.navigation;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import site.nomoreparties.stellarburgers.helpers.BrowserRules;
import site.nomoreparties.stellarburgers.helpers.Utils;
import site.nomoreparties.stellarburgers.model.UserData;
import site.nomoreparties.stellarburgers.pom_pages.LoginPage;
import site.nomoreparties.stellarburgers.pom_pages.MainPage;
import site.nomoreparties.stellarburgers.pom_pages.ProfilePage;

import static org.junit.Assert.assertTrue;

public class ProfilePageNavigationTests {
    private final Utils utils = new Utils();
    @Rule
    public BrowserRules browserRules = new BrowserRules(BrowserRules.CHROME);
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private UserData loginData;

    @Step("Выполнить предварительные действия для теста навигации по странице профиля")
    @Before
    public void localSetUp() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        profilePage = new ProfilePage(browserRules.getDriver());
        loginData = utils.defaultLoginData();
    }

    @DisplayName("Переход из Личного кабинета на Главную страницу. Успешно")
    @Description("Проверяет, что авторизованный пользователь успешно переходит из личного кабинета на главную страницу")
    @Test
    public void goToHomePageFromProfileSuccess() {
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.logIn(loginData);
        mainPage.clickUserProfile();
        profilePage.goToMainPage();

        assertTrue(mainPage.isCurrentPositionMainPageAuthorized());
    }

    @DisplayName("Переход из страницы Авторизации на Главную. Успешно")
    @Description("Проверяет, что неавторизованный пользователь успешно переходит из страницы авторизации на главную страницу")
    @Test
    public void goToHomePageFromLoginPage() {
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.goToMainPage();

        assertTrue(mainPage.isCurrentPositionMainPageNotAuthorized());
    }
}
