package site.nomoreparties.stellarburgers.tests.navigation;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import site.nomoreparties.stellarburgers.helpers.BrowserRules;
import site.nomoreparties.stellarburgers.pom_pages.ConstructorPage;
import site.nomoreparties.stellarburgers.pom_pages.MainPage;

import static org.junit.Assert.assertEquals;

public class ConstructorNavigationTests {
    @Rule
    public BrowserRules browserRules = new BrowserRules(BrowserRules.CHROME);
    private MainPage mainPage;
    private ConstructorPage constructorPage;

    @Step("Выполнить предварительные действия для теста навигации по странице конструктора")
    @Before
    public void localSetUp() {
        mainPage = new MainPage(browserRules.getDriver());
        constructorPage = new ConstructorPage(browserRules.getDriver());
    }

    @DisplayName("Переход из раздела Булки в Начинки. Успешно")
    @Description("Проверяет, что успешно выполняется переход из раздела Булки к разделу Начинки, отображаются ингредиенты раздела")
    @Test
    public void switchFromBunToFillingTab() {
        mainPage.open();
        mainPage.goToBurgerConstructor();
        constructorPage.waitForConstructorPageDownload();

        int bunLocation = constructorPage.getBunLocation();
        constructorPage.switchToFillingTab();

        int fillingLocation = constructorPage.getFillingLocation();

        assertEquals(bunLocation, fillingLocation, 50);
    }

    @DisplayName("Переход из раздела Булки в Соусы. Успешно")
    @Description("Проверяет, что успешно выполняется переход из раздела Булки к разделу Соусы, отображаются ингредиенты раздела")
    @Test
    public void switchFromBunToSauceTab() {
        mainPage.open();
        mainPage.goToBurgerConstructor();

        int bunLocation = constructorPage.getBunLocation();

        constructorPage.switchToSauceTab();

        int sauceLocation = constructorPage.getSauceLocation();

        assertEquals(bunLocation, sauceLocation, 50);
    }

    @DisplayName("Переход из раздела Соусы в Начинки. Успешно")
    @Description("Проверяет, что успешно выполняется переход из раздела Соусы к разделу Начинки, отображаются ингредиенты раздела")
    @Test
    public void switchFromSauceToFillingTab() {
        mainPage.open();
        mainPage.goToBurgerConstructor();
        constructorPage.switchToSauceTab();

        int sauceLocation = constructorPage.getSauceLocation();

        constructorPage.switchToFillingTab();

        int fillingLocation = constructorPage.getFillingLocation();

        assertEquals(sauceLocation, fillingLocation, 50);
    }

}
