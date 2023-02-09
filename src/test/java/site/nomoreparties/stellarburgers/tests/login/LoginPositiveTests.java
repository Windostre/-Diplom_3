package site.nomoreparties.stellarburgers.tests.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.pom_pages.LoginPage;
import site.nomoreparties.stellarburgers.pom_pages.MainPage;
import site.nomoreparties.stellarburgers.pom_pages.RegisterPage;
import site.nomoreparties.stellarburgers.pom_pages.RestorePasswordPage;

import java.time.Duration;

import static org.junit.Assert.*;

public class LoginPositiveTests {
    private WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    RestorePasswordPage restorePasswordPage;
    private String existingEmail = "some_client@mail.com";
    private String existingPassword = "123456";


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        restorePasswordPage = new RestorePasswordPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void loginViaUserProfileSuccess() {
        mainPage.open();
        mainPage.goToUserProfile();
        if(loginPage.isCurrentPositionLoginPage()) {
            loginPage.logIn(existingEmail, existingPassword);
        }

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

    @Test
    public void loginViaSignInButtonSuccess() {
        mainPage.goToLoginPage();
        loginPage.logIn(existingEmail, existingPassword);

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

    @Test
    public void loginViaRegistrationFormSuccess() {
        mainPage.goToLoginPage();
        loginPage.goToRegisterPage();
        registerPage.goToLoginPage();
        loginPage.logIn(existingEmail, existingPassword);

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

    @Test
    public void loginViaForgotPasswordPageSuccess() {
        mainPage.goToLoginPage();
        loginPage.goToRestorePasswordPage();
        restorePasswordPage.goToLoginPage();
        loginPage.logIn(existingEmail, existingPassword);

        assertTrue(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }

}
