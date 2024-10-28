package Lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lesson15 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://mts.by");

        // Принять куки
        WebElement acceptCookiesButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("bxdynamic_cookies_agreement-pt5_start")));
        acceptCookiesButton.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void testCheckHeaderText() {
        // Проверка заголовка блока "Онлайн пополнение без комиссии"
        WebElement header = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.header-title"))
        );
        assertEquals("Онлайн пополнение без комиссии", header.getText());
    }

    @Test
    public void testCheckPaymentServiceOption() {
        // Проверка наличия опции "Услуги связи"
        WebElement serviceOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("select__header"))
        );
        assertTrue(serviceOption.isDisplayed(), "Опция 'Услуги связи' не найдена");
    }

    @Test
    public void testFillServiceFields() {
        // Заполнение полей для "Услуги связи"
        WebElement serviceOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("select__header"))
        );
        serviceOption.click();

        WebElement phoneNumberField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone"))
        );
        phoneNumberField.sendKeys("297777777");

        WebElement continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Продолжить')]"))
        );
        continueButton.click();
    }

    @Test
    public void testVerifyPaymentDetails() {
        // Проверка отображения информации после нажатия кнопки "Продолжить"
        WebElement amountElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("button__default"))
        );
        assertEquals("100", amountElement.getText(), "Сумма не совпадает");

        WebElement phoneNumberElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone"))
        );
        assertEquals("297777777", phoneNumberElement.getText(), "Номер телефона не совпадает");

        // Проверка наличия иконок платёжных систем
        List<WebElement> paymentIcons = driver.findElements(By.className("pay__partners"));
        assertTrue(paymentIcons.size() > 0, "Иконки платёжных систем не найдены");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}