package site.nomoreparties.stellarburgers.tests.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.helpers.BrowserRules;
import site.nomoreparties.stellarburgers.helpers.Utils;
import site.nomoreparties.stellarburgers.pom_pages.LoginPage;
import site.nomoreparties.stellarburgers.pom_pages.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginNegativeParamTests {
    @Rule
    public BrowserRules browserRules = new BrowserRules(BrowserRules.CHROME);

    private static String randomEmail() {
        Utils utils = new Utils();
        return utils.generateRandomEmail();
    }
    private static String randomPassword() {
        Utils utils = new Utils();
        return utils.generateRandomPassword();
    }
    @Parameterized.Parameter(0)
    public String comment;
    @Parameterized.Parameter(1)
    public String email;
    @Parameterized.Parameter(2)
    public String password;

    @Parameterized.Parameters(name = "comment : {0}, email : {1}, password : {2}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Пользователь не существует",randomEmail(), randomPassword()},
                {"Правильный логин, неправильный пароль","some_client@mail.com", randomPassword()},
                {"Неравильный логин, правильный пароль",randomEmail(), "123456"},
                {"Правильный логин, пустой пароль","some_client@mail.com", ""},
                {"Пустой логин, правильный пароль","", "123456"},
        };

    }

    @Test
    public void loginFailWhenInvalidDataProvided(){
        MainPage mainPage = new MainPage(browserRules.getDriver());
        LoginPage loginPage = new LoginPage(browserRules.getDriver());

        mainPage.open()
                .goToLoginPage();
        loginPage
                .logIn(email,password);

        assertTrue(loginPage.isCurrentPositionLoginPage());
        assertFalse(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }
}
