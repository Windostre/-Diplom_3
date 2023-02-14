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

public class MainPageNavigationTests {
    private final Utils utils = new Utils();
    @Rule
    public BrowserRules browserRules = new BrowserRules(BrowserRules.CHROME);
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private UserData loginData;

    @Step("Выполнить предварительные действия для теста навигации по главной странице")
    @Before
    public void localSetUp() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        profilePage = new ProfilePage(browserRules.getDriver());
        loginData = utils.defaultLoginData();
    }

    @DisplayName("Переход из Главной страницы на страницу Профиля. Пользователь Авторизован. Успешно")
    @Description("Проверяет, что авторизованный пользователь при клике на Личный кабинет успешно переходит из главной страницы в свой профиль")
    @Test
    public void goToProfileFromMainPageSuccessWhenAuthorized() {
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.logIn(loginData);
        mainPage.clickUserProfile();

        assertTrue(profilePage.isCurrentPositionProfilePage());
    }

    @DisplayName("Переход из Главной страницы на страницу авторизации. Пользователь неавторизован. Успешно")
    @Description("Проверяет, что неавторизованный пользователь при клике на Личный кабинет успешно переходит из главной страницы на страницу авторизации")
    @Test
    public void goToProfileFromMainPageFailWhenNotAuthorized() {
        mainPage.open();
        mainPage.clickUserProfile();

        assertTrue(loginPage.isCurrentPositionLoginPage());
    }
}
