package site.nomoreparties.stellarburgers.tests.register;

import io.restassured.http.ContentType;
import org.junit.After;
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

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class UserRegistrationTests {
    @Rule
    public BrowserRules browserRules = new BrowserRules(BrowserRules.CHROME);

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ProfilePage profilePage;
    private String accessToken;

    private Utils utils = new Utils();
    private UserData userData;
    private UserData loginData;

    @Before
    public void localSetUp() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        registerPage = new RegisterPage(browserRules.getDriver());
        profilePage = new ProfilePage(browserRules.getDriver());
        userData = utils.generateRandomUser();
        loginData = UserData.builder().email(userData.getEmail()).password(userData.getPassword()).build();

    }

    @After
    public void localTearDown() throws InterruptedException {
        loginPage.logIn(loginData);
        accessToken = loginPage.getAccessToken(); //Для удаления
        if (accessToken == null || "".equals(accessToken)) {
            return;
        }
        profilePage.deleteUserViaApi(accessToken);


    }

    @Test
    public void registerNewUserViaUserProfileSuccess() {
        mainPage.open()
                .clickUserProfile();
        loginPage.goToRegisterPage();
        registerPage.fillSignInForm(userData)
                    .submitSignIn();

        assertTrue(loginPage.isCurrentPositionLoginPage());

    }

}
