import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.HomePage;
import pageObjects.OrderPage;
import java.time.Duration;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class OrderScooterParametrizedPositiveScenario {
    private WebDriver driver;
    private final String name;
    private final String lastname;
    private final String address;
    private final String station;
    private final String phone;
    private final String deliveryDate;
    private final String comment;

    public OrderScooterParametrizedPositiveScenario(String name, String lastname, String address, String station, String phone, String deliveryDate, String comment) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getUserTestData() {
        return new Object [][] {
                {"Дмитрий", "Домовенко", "Лесная 5", "Коломенская", "+79231112233", "01.04.2026", "Буду кататься всю ночь напролет"},
                {"Ольга", "Романовская", "Сербская 10", "Войковская", "+79257776655", "05.04.2026", "Оставьте у двери"}
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void OrderScooterParametrizedPositiveScenarioTest() {
        OrderPage orderPage = new OrderPage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickTopOrderButton();
        String expectedText = "Для кого самокат";
        String actualText = homePage.getOrderForm();
        assertEquals(expectedText, actualText);

        orderPage.setPersonalInfo(name, lastname, address, station, phone);
        orderPage.clickNextButton();
        String expectedAboutRentText = "Про аренду";
        String actualAboutRentText = orderPage.getAboutRentText();
        assertEquals(expectedAboutRentText, actualAboutRentText);
        orderPage.setWhenDeliverField(deliveryDate);
        orderPage.setRentalTimeDropdown();
        orderPage.choseGreyScooter();
        orderPage.setCommentField(comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmButton(); // на этом шаге тест падает из-за бага при тестировании в Хроме
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
