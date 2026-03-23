import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.HomePage;
import pageObjects.OrderPage;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class OrderScooterPositiveScenario {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Тест 1: проверка верхней кнопки "Заказать"

    @Test
    public void topOrderButtonOpensRegistrationForm() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickTopOrderButton();
        homePage.getOrderForm();
        String expectedText = "Для кого самокат";
        String actualText = homePage.getOrderForm();
        assertEquals(expectedText, actualText);
    }

    // Тест 2: проверка нижней кнопки "Заказать"
    @Test
    public void bottomOrderButtonOpensRegistrationForm() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickBottomOrderButton();
        homePage.getOrderForm();
        String expectedText = "Для кого самокат";
        String actualText = homePage.getOrderForm();
        assertEquals(expectedText, actualText);
    }

    // Тест 3: позитивный сенарий с одним набором данных
    @Test
    public void OrderScooterPositiveScenarioTest() {
        OrderPage orderPage = new OrderPage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickTopOrderButton();
        orderPage.setPersonalInfo("Олег", "Орлов", "Орлова 1", "Таганская", "+79235554433");
        orderPage.clickNextButton();
        String expectedAboutRentText = "Про аренду";
        String actualAboutRentText = orderPage.getAboutRentText();
        assertEquals(expectedAboutRentText, actualAboutRentText);
        orderPage.setWhenDeliverField("30.03.2026");
        orderPage.setRentalTimeDropdown();
        orderPage.choseBlackScooter();
        orderPage.setCommentField("Привезите чистым, пожалуйста");
        orderPage.clickOrderButton();
        orderPage.clickConfirmButton(); // на этом шаге тест падает из-за бага при тестировании в Хроме
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
