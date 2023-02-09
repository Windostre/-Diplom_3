package site.nomoreparties.stellarburgers.tests.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.helpers.BrowserRules;
import site.nomoreparties.stellarburgers.pom_pages.LoginPage;
import site.nomoreparties.stellarburgers.pom_pages.MainPage;
import site.nomoreparties.stellarburgers.pom_pages.RegisterPage;
import site.nomoreparties.stellarburgers.pom_pages.RestorePasswordPage;

import java.time.Duration;

import static org.junit.Assert.*;

public class LoginPositiveTests {
    @Rule
    public BrowserRules browserRules = new BrowserRules(BrowserRules.CHROME);
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    RestorePasswordPage restorePasswordPage;
    private String existingEmail = "some_client@mail.com";
    private String existingPassword = "123456";

    @Test
    public void loginViaUserProfileSuccess() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        registerPage = new RegisterPage(browserRules.getDriver());
        restorePasswordPage = new RestorePasswordPage(browserRules.getDriver());
        mainPage.open()
                .clickUserProfile();

        if(loginPage.isCurrentPositionLoginPage()) {
            loginPage.logIn(existingEmail, existingPassword);
        }

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

    @Test
    public void loginViaSignInButtonSuccess() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        registerPage = new RegisterPage(browserRules.getDriver());
        restorePasswordPage = new RestorePasswordPage(browserRules.getDriver());
        mainPage.open()
                .goToLoginPage();
        loginPage.logIn(existingEmail, existingPassword);

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

    @Test
    public void loginViaRegistrationFormSuccess() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        registerPage = new RegisterPage(browserRules.getDriver());
        restorePasswordPage = new RestorePasswordPage(browserRules.getDriver());
        mainPage.open()
                .goToLoginPage();
        loginPage.goToRegisterPage();
        registerPage.goToLoginPage();
        loginPage.logIn(existingEmail, existingPassword);

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

    @Test
    public void loginViaForgotPasswordPageSuccess() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        registerPage = new RegisterPage(browserRules.getDriver());
        restorePasswordPage = new RestorePasswordPage(browserRules.getDriver());
        mainPage.open()
                .goToLoginPage();
        loginPage.goToRestorePasswordPage();
        restorePasswordPage.goToLoginPage();
        loginPage.logIn(existingEmail, existingPassword);

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

}
