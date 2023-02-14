package site.nomoreparties.stellarburgers.tests.login_sessions;

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
import site.nomoreparties.stellarburgers.pom_pages.RegisterPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class LogoutTests {
    private final Utils utils = new Utils();
    @Rule
    public BrowserRules browserRules = new BrowserRules(BrowserRules.CHROME);
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ProfilePage profilePage;
    private UserData loginData;

    @Step("Выполнить предварительные действия для теста выхода из приложения")
    @Before
    public void localSetUp() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        registerPage = new RegisterPage(browserRules.getDriver());
        profilePage = new ProfilePage(browserRules.getDriver());
        loginData = utils.defaultLoginData();
    }

    @DisplayName("Выход из системы через личный кабинет. Успешно")
    @Description("Проверяет, что при логауте пользователь выходит из приложения, осуществляется переход на старницу авторизации")
    @Test
    public void logoutViaProfileSuccess() throws InterruptedException {
        mainPage.open();
        mainPage.clickUserProfile();
        loginPage.logIn(loginData);
        mainPage.clickUserProfile();

        assertTrue(profilePage.isCurrentPositionProfilePage());

        profilePage.logout();

        assertThat(loginPage.getAccessToken(), is(nullValue()));
        assertTrue(loginPage.isCurrentPositionLoginPage());
    }


}
