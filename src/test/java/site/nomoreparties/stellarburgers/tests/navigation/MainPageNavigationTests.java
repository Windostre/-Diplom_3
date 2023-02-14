package site.nomoreparties.stellarburgers.tests.navigation;

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

    @Before
    public void localSetUp() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        profilePage = new ProfilePage(browserRules.getDriver());
        loginData = utils.defaultLoginData();
    }

    @Test
    public void goToProfileFromMainPageSuccessWhenAuthorized() {
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.logIn(loginData);
        mainPage.clickUserProfile();

        assertTrue(profilePage.isCurrentPositionProfilePage());
    }

    @Test
    public void goToProfileFromMainPageFailWhenNotAuthorized() {
        mainPage.open();
        mainPage.clickUserProfile();

        assertTrue(loginPage.isCurrentPositionLoginPage());
    }
}
