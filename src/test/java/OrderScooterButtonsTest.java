import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObjects.FactoryDriver;
import pageObjects.HomePage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderScooterButtonsTest {
    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();
    private final String buttonType;

    public OrderScooterButtonsTest(String buttonType) {
        this.buttonType = buttonType;
    }

    @Parameterized.Parameters
    public static Object[][] getUserButtonClick() {
        return new Object[][]{
                {"top"},
                {"bottom"}
        };
    }

        @Test
        public void orderButtonOpensRegistrationForm () {
            WebDriver driver = factoryDriver.getDriver();
            driver.manage().window().maximize();
            HomePage homePage = new HomePage(driver);
            homePage.openHomePage();
            if ("top".equals(buttonType)) {
                homePage.clickTopOrderButton();
            } else {
                homePage.clickBottomOrderButton();
            }
            homePage.getOrderForm();
            String expectedText = "Для кого самокат";
            String actualText = homePage.getOrderForm();
            assertEquals(expectedText, actualText);
        }
    }