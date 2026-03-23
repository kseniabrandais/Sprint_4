package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class HomePage {
    private WebDriver driver;
    private final By firstQuestion = By.id("accordion__heading-0");
    private final By answerToFirstQuestion = By.id("accordion__panel-0");

    private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']//button[text()='Заказать']");
    private final By bottomOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");
    private final By orderForm = By.xpath("//div[text()='Для кого самокат']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openHomePage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    // методы для проверки секции вопросов

    public void scrollDowntoQuestions() {
        WebElement questions = driver.findElement(firstQuestion);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questions);
    }

    public void clickOnFirstQuestion() {
        driver.findElement(firstQuestion).click();
    }
    public String getFirstAnswerText() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(answerToFirstQuestion));
        return driver.findElement(answerToFirstQuestion).getText();
    }

    // методы для кнопок "Заказать"

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }
    public void scrollDownToBottomOrderButton() {
        WebElement elementBottomOrderButton = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elementBottomOrderButton);
    }

    public void clickBottomOrderButton() {
        scrollDownToBottomOrderButton();
        driver.findElement(bottomOrderButton).click();
    }
    public String getOrderForm() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderForm));
        return driver.findElement(orderForm).getText();
    }
}
