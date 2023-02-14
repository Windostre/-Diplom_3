package site.nomoreparties.stellarburgers.tests.navigation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import site.nomoreparties.stellarburgers.helpers.BrowserRules;
import site.nomoreparties.stellarburgers.pom_pages.ConstructorPage;
import site.nomoreparties.stellarburgers.pom_pages.MainPage;

import static org.junit.Assert.assertEquals;

public class BurgerConstructorNavigationTests {
    @Rule
    public BrowserRules browserRules = new BrowserRules(BrowserRules.CHROME);
    private MainPage mainPage;
    private ConstructorPage constructorPage;

    @Before
    public void localSetUp() {
        mainPage = new MainPage(browserRules.getDriver());
        constructorPage = new ConstructorPage(browserRules.getDriver());
    }

    @Test
    public void switchFromBunToFillingTab() {
        mainPage.open();
        mainPage.goToBurgerConstructor();
        constructorPage.waitForBunPageDownload();

        int bunLocation = constructorPage.getBunLocation();
        constructorPage.switchToFillingTab();

        int fillingLocation = constructorPage.getFillingLocation();

        assertEquals(bunLocation, fillingLocation, 50);
    }

    @Test
    public void switchFromBunToSauceTab() {
        mainPage.open();
        mainPage.goToBurgerConstructor();

        int bunLocation = constructorPage.getBunLocation();
        System.out.println(bunLocation);

        constructorPage.switchToSauceTab();

        int sauceLocation = constructorPage.getSauceLocation();
        System.out.println(sauceLocation);

        assertEquals(bunLocation, sauceLocation, 50);
    }

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
