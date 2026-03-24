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
    private static final String HOME_PAGE_URL = "https://qa-scooter.praktikum-services.ru/"; // константа для URL

    private final By questionsSection = By.xpath("//div[@class='Home_FAQ__3uVm4']");

    private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']//button[text()='Заказать']");
    private final By bottomOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");
    private final By orderForm = By.xpath("//div[text()='Для кого самокат']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void openHomePage() {
        driver.get(HOME_PAGE_URL);
    }

    // методы для проверки секции вопросов

    public void scrollDowntoQuestions() {
        WebElement questions = driver.findElement(questionsSection);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questions);
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(questions));
    }

    private By question(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By answer(int index) {
        return By.id("accordion__panel-" + index);
    }

    public void clickOnQuestion(int index) {
        driver.findElement(question(index)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(question(index)));
    }
    public String getAnswerText(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(answer(index)));
        WebElement answerElement = driver.findElement(answer(index));
            return answerElement.getText();
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
