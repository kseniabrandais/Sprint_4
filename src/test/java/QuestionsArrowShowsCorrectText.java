import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.HomePage;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class QuestionsArrowShowsCorrectText {
    private WebDriver driver;


    @Before
    public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void questionsArrowShowsCorrectText() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.scrollDowntoQuestions();
        homePage.clickOnFirstQuestion();
        homePage.getFirstAnswerText();
        String expectedText = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        String actualText = homePage.getFirstAnswerText();
        assertEquals(expectedText, actualText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}