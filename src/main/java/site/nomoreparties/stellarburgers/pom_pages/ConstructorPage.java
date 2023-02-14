package site.nomoreparties.stellarburgers.pom_pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Описание конструктора на главной странице
 */
public class ConstructorPage {
    public final By headerBun = By.xpath("//h2[text()='Булки']");
    public final By headerSauce = By.xpath("//h2[text()='Соусы']");
    public final By headerFilling = By.xpath("//h2[text()='Начинки']");
    private final WebDriver driver;
    /* POM локаторы */
    //Вкладка "Булки"
    private final By bunTab = By.xpath("//span[text()='Булки']");
    //Вкладка "Соусы"
    private final By sauceTab = By.xpath("//span[text()='Соусы']");
    //Вкладка "Начинки"
    private final By fillingTab = By.xpath("//span[text()='Соусы']");
    //Список ингридиентов
    private final By ingredientsList = By.className("BurgerIngredients_ingredients__1N8v2");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    /* POM методы */
    @Step("Перейти на вкладку Булки")
    public void switchToBunTab() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", driver.findElement(headerBun));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(bunTab).click();
    }

    @Step("Подождать пока загрузиться страница Конструктора")
    public void waitForConstructorPageDownload() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(headerBun));
    }

    @Step("Перейти на вкладку Соусы")
    public void switchToSauceTab() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", driver.findElement(headerSauce));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(sauceTab).click();
    }

    @Step("Перейти на вкладку Начинки")
    public void switchToFillingTab() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", driver.findElement(headerFilling));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(fillingTab).click();
    }

    @Step("Получить координаты вкладки Начинки")
    public int getFillingLocation() {
        return driver.findElement(headerFilling).getLocation().y;
    }

    @Step("Получить координаты вкладки Булки")
    public int getBunLocation() {
        return driver.findElement(headerBun).getLocation().y;
    }

    @Step("Получить координаты вкладки Соусы")
    public int getSauceLocation() {
        return driver.findElement(headerSauce).getLocation().y;
    }
}

