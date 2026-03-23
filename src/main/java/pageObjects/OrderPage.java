package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class OrderPage {
    private WebDriver driver;

    private final By nameField = By.xpath("//input[@placeholder='* Имя']"); // поле Имя
    private final By lastnameField = By.xpath("//input[@placeholder='* Фамилия']"); // поле Фамилия
    private final By addressField = By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']");// поле Адрес
    private final By metroStationField = By.className("select-search__input"); // поле Станций метро
    private final By stationOption = By.xpath("//div[(@class='select-search__select')]//div[text()='Таганская']");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']"); // поле Телефон
    private final By nextButton = By.cssSelector("[class='Button_Button__ra12g Button_Middle__1CSJM']"); // кнопка Далле

    private final By aboutRentForm = By.xpath("//div[text()='Про аренду']");
    private final By whenDeliverField = By.xpath("//input[@placeholder='* Когда привезти самокат']"); // поле Когда привезти самокат

    private final By rentalTimeDropdown = By.xpath("//div[@class='Dropdown-root']"); // выпадающий список Срок аренды
    private final By rentalTimeList = By.xpath("//div[@class='Dropdown-menu']");
    private final By rentalTimeTwoDays = By.xpath("//div[text()='двое суток']");
    private final By blackScooterCheckbox = By.id("black"); // выбор галочкой списка Цвет самоката
    private final By greyScooterCheckbox = By.id("grey");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']"); // поле Комментарий для курьера
    private final By orderButton = By.cssSelector("[class='Button_Button__ra12g Button_Middle__1CSJM']");    // кнопка Заказать
    private final By wantOrderText = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

    private final By confirmButton = By.xpath("//button[text()='Да']"); // кнопка подтверждения заказа Да
    private final By confirmationMessage = By.xpath("//div[contains(text(), 'Заказ оформлен')]"); // окно потверждения заказа Заказ оформлен

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // методы для формы заказа - окно с персональными данными

    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    public void setLastnameField(String lastname) {
        driver.findElement(lastnameField).sendKeys(lastname);
    }
    public void setAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    public void setMetroStationField(String station) {
        driver.findElement(metroStationField).click();
        driver.findElement(metroStationField).sendKeys(station);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(stationOption));
        driver.findElement(stationOption).click(); // кликнуть на строку станции
    }
    public void setPhoneField(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }
    public void setPersonalInfo(String name, String lastname, String address, String station, String phone) {
        setNameField(name);
        setLastnameField(lastname);
        setAddressField(address);
        setMetroStationField(station);
        setPhoneField(phone);
        clickNextButton();
    }
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    public String getAboutRentText() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(aboutRentForm));
        return driver.findElement(aboutRentForm).getText();
    }

    // методы для формы заказа - окно с деталями заказа

    public void setWhenDeliverField(String deliveryDate) {
        driver.findElement(whenDeliverField).sendKeys(deliveryDate);
        driver.findElement(whenDeliverField).sendKeys(Keys.ENTER);
    }
    public void setRentalTimeDropdown() {
        driver.findElement(rentalTimeDropdown).click();
            new WebDriverWait(driver, Duration.ofSeconds(10)) // подождать раскрытия списка
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rentalTimeList));
        driver.findElement(rentalTimeTwoDays).click(); // кликнуть на конкретную строку
    }

    public void choseBlackScooter() {
        driver.findElement(blackScooterCheckbox).click();
    }
    public void choseGreyScooter() {
        driver.findElement(greyScooterCheckbox).click();
    }
    public void setCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(wantOrderText));
    }

    // методы для формы заказа - окно с подтверждением заказа

    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
    }

}
