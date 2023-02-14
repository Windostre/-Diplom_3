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
import site.nomoreparties.stellarburgers.pom_pages.ForgotPasswordPage;
import site.nomoreparties.stellarburgers.pom_pages.LoginPage;
import site.nomoreparties.stellarburgers.pom_pages.MainPage;
import site.nomoreparties.stellarburgers.pom_pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class LoginPositiveTests {
    private final Utils utils = new Utils();
    @Rule
    public BrowserRules browserRules = new BrowserRules(BrowserRules.CHROME);
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private UserData userData;

    @Step("Выполнить предварительные действия для теста авторизацию")
    @Before
    public void localSetUp() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        registerPage = new RegisterPage(browserRules.getDriver());
        forgotPasswordPage = new ForgotPasswordPage(browserRules.getDriver());
        userData = utils.defaultLoginData();
    }

    @DisplayName("Авторизация пользователя через личный кабинет. Успешно")
    @Description("Проверяет, что при вводе валидного логина и пароля пользователь успешно авторизуется и переходит на главную страницу")
    @Test
    public void loginViaUserProfileSuccess() {
        mainPage.open();
        mainPage.clickUserProfile();

        if (loginPage.isCurrentPositionLoginPage()) {
            loginPage.logIn(userData);
        }

        assertTrue(mainPage.isCurrentPositionMainPageAuthorized());
    }

    @DisplayName("Авторизация пользователя через вход в аккаунт. Успешно")
    @Description("Проверяет, что при вводе валидного логина и пароля пользователь успешно авторизуется и переходит на главную страницу")
    @Test
    public void loginViaSignInButtonSuccess() {
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.logIn(userData);

        assertTrue(mainPage.isCurrentPositionMainPageAuthorized());
    }

    @Test
    @DisplayName("Авторизация пользователя через форму регистрации. Успешно")
    @Description("Проверяет, что при вводе валидного логина и пароля пользователь успешно авторизуется и переходит на главную страницу")
    public void loginViaRegistrationFormSuccess() {
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.goToRegisterPage();
        registerPage.goToLoginPage();
        loginPage.logIn(userData);

        assertTrue(mainPage.isCurrentPositionMainPageAuthorized());
    }

    @DisplayName("Авторизация пользователя через форму восстановления пароля. Успешно")
    @Description("Проверяет, что при вводе валидного логина и пароля пользователь успешно авторизуется и переходит на главную страницу")
    @Test
    public void loginViaForgotPasswordPageSuccess() throws InterruptedException {
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.goToRestorePasswordPage();
        forgotPasswordPage.goToLoginPage();
        loginPage.logIn(userData);

        assertTrue(mainPage.isCurrentPositionMainPageAuthorized());
    }

}
