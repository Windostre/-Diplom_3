package site.nomoreparties.stellarburgers.tests.login;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import site.nomoreparties.stellarburgers.helpers.BrowserRules;
import site.nomoreparties.stellarburgers.helpers.Utils;
import site.nomoreparties.stellarburgers.model.UserData;
import site.nomoreparties.stellarburgers.pom_pages.LoginPage;
import site.nomoreparties.stellarburgers.pom_pages.MainPage;
import site.nomoreparties.stellarburgers.pom_pages.RegisterPage;
import site.nomoreparties.stellarburgers.pom_pages.ForgotPasswordPage;

import static org.junit.Assert.*;

public class LoginPositiveTests {
    @Rule
    public BrowserRules browserRules = new BrowserRules(BrowserRules.CHROME );
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private Utils utils = new Utils();
    private UserData userData;

    @Before
    public void setUp() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        registerPage = new RegisterPage(browserRules.getDriver());
        forgotPasswordPage = new ForgotPasswordPage(browserRules.getDriver());
        userData = utils.defaultLoginData();
    }

    @Test
    public void loginViaUserProfileSuccess() {
        mainPage.open()
                .clickUserProfile();

        if(loginPage.isCurrentPositionLoginPage()) {
            loginPage.logIn(userData);
        }

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

    @Test
    public void loginViaSignInButtonSuccess() {
        mainPage.open()
                .goToLoginPage();
        loginPage.logIn(userData);

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

    @Test
    public void loginViaRegistrationFormSuccess() {
        mainPage.open()
                .goToLoginPage();
        loginPage.goToRegisterPage();
        registerPage.goToLoginPage();
        loginPage.logIn(userData);

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

    @Test
    public void loginViaForgotPasswordPageSuccess() {
        mainPage.open()
                .goToLoginPage();
        loginPage.goToRestorePasswordPage();
        forgotPasswordPage.goToLoginPage();
        loginPage.logIn(userData);

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

}
