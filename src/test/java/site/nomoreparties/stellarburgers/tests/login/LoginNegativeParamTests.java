package site.nomoreparties.stellarburgers.tests.login;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import site.nomoreparties.stellarburgers.helpers.BrowserRules;
import site.nomoreparties.stellarburgers.helpers.Utils;
import site.nomoreparties.stellarburgers.pom_pages.LoginPage;
import site.nomoreparties.stellarburgers.pom_pages.MainPage;

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
    private static String defaultEmail() {
        Utils utils = new Utils();
        return utils.DEFAULT_EMAIL;
    }
    private static String defaultPassword() {
        Utils utils = new Utils();
        return utils.DEFAULT_PASSWORD;
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
                {"Правильный логин, неправильный пароль",defaultEmail(), randomPassword()},
                {"Неравильный логин, правильный пароль",randomEmail(), defaultPassword()},
                {"Правильный логин, пустой пароль",defaultEmail(), ""},
                {"Пустой логин, правильный пароль","", defaultPassword()},
        };

    }

    @Test
    public void loginFailWhenInvalidDataProvided(){
        MainPage mainPage = new MainPage(browserRules.getDriver());
        LoginPage loginPage = new LoginPage(browserRules.getDriver());

        mainPage.open()
                .goToLoginPage();
        loginPage
                .logInString(email,password);

        assertTrue(loginPage.isCurrentPositionLoginPage());
        assertFalse(mainPage.isCurrentPositionMainPageWhenLoggedIn());

    }
}
