package site.nomoreparties.stellarburgers.tests.register;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import site.nomoreparties.stellarburgers.helpers.BrowserRules;
import site.nomoreparties.stellarburgers.helpers.Utils;
import site.nomoreparties.stellarburgers.pom_pages.LoginPage;
import site.nomoreparties.stellarburgers.pom_pages.MainPage;
import site.nomoreparties.stellarburgers.pom_pages.ProfilePage;
import site.nomoreparties.stellarburgers.pom_pages.RegisterPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserRegistrationTests {
    private final Utils utils = new Utils();
    @Rule
    public BrowserRules browserRules = new BrowserRules(BrowserRules.CHROME);
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ProfilePage profilePage;
    private String name;
    private String email;
    private String password;
    private String accessToken;

    @Step("Выполнить предварительные действия для теста создания пользователя")
    @Before
    public void localSetUp() {
        mainPage = new MainPage(browserRules.getDriver());
        loginPage = new LoginPage(browserRules.getDriver());
        registerPage = new RegisterPage(browserRules.getDriver());
        profilePage = new ProfilePage(browserRules.getDriver());
        name = utils.generateRandomName();
        email = utils.generateRandomEmail();
        password = utils.generateRandomPassword();
    }

    @Step("Выполнить постусловия по удалению тестовых данных")
    @After
    public void localTearDown() throws InterruptedException {
        if (!loginPage.isCurrentPositionLoginPage()) {
            return;
        }
        loginPage.logInString(email, password);
        accessToken = loginPage.getAccessToken(); //Для удаления
        if (accessToken == null || "".equals(accessToken)) {
            return;
        }
        profilePage.deleteUserViaApi(accessToken);
    }

    @DisplayName("Создание пользователя через личный кабинет. Успешно")
    @Description("Проверяет, что при вводе валидных почты, имени и пароля пользователь успешно создан и переходит на страницу авторизации")
    @Test
    public void registerNewUserViaUserProfileSuccess() {
        mainPage.open();
        mainPage.clickUserProfile();
        loginPage.goToRegisterPage();
        registerPage.fillSignInForm(name, email, password);
        registerPage.submitSignIn();

        assertTrue(loginPage.isCurrentPositionLoginPage());
    }

    @DisplayName("Создание пользователя через форму авторизации. Успешно")
    @Description("Проверяет, что при вводе валидных почты, имени и пароля пользователь успешно создан и переходит на страницу авторизации")
    @Test
    public void registerNewUserViaLoginPageSuccess() {
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.goToRegisterPage();
        registerPage.fillSignInForm(name, email, password);
        registerPage.submitSignIn();

        assertTrue(loginPage.isCurrentPositionLoginPage());
    }

    @DisplayName("Создание пользователя. Пустое имя. Провал")
    @Description("Проверяет, что при нельзя создать пользователя если не заполнено имя")
    @Test
    public void registerNewUserFailWhenNameIsEmpty() {
        name = "";
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.goToRegisterPage();
        registerPage.fillSignInForm(name, email, password);
        registerPage.submitSignIn();

        assertTrue(registerPage.isCurrentPositionRegisterPage());
    }

    @DisplayName("Создание пользователя. Пустая почта. Провал")
    @Description("Проверяет, что при нельзя создать пользователя если не заполнена почта")
    @Test
    public void registerNewUserFailWhenEmailIsEmpty() {
        email = "";
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.goToRegisterPage();
        registerPage.fillSignInForm(name, email, password);
        registerPage.submitSignIn();

        assertTrue(registerPage.isCurrentPositionRegisterPage());
    }

    @DisplayName("Создание пользователя. Пустой пароль. Провал")
    @Description("Проверяет, что при нельзя создать пользователя если не заполнен пароль")
    @Test
    public void registerNewUserFailWhenPasswordIsEmpty() {
        password = "";
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.goToRegisterPage();
        registerPage.fillSignInForm(name, email, password);
        registerPage.submitSignIn();

        assertTrue(registerPage.isCurrentPositionRegisterPage());
    }

    @DisplayName("Создание пользователя. Пароль < 6 символов. Провал")
    @Description("Проверяет, что при нельзя создать пользователя если не пароль меньше 6 символов, получена ошибка")
    @Test
    public void registerNewUserFailWhenPasswordIsShort() {
        password = utils.generateShortPassword();
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.goToRegisterPage();
        registerPage.fillSignInForm(name, email, password);
        registerPage.submitSignIn();

        String actualMessage = registerPage.getErrorMessage();

        assertEquals("Некорректный пароль", actualMessage);
        assertTrue(registerPage.isCurrentPositionRegisterPage());
    }

}
