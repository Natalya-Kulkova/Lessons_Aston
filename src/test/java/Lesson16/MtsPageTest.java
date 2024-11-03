import Lesson16.MtsPage;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsPageTest {
    private WebDriver driver; // Инициализация WebDriver
    private MtsPage mtsPage;

    @BeforeEach
    public void setUp() {
        // Инициализация WebDriver и открытие страницы
        driver = new ChromeDriver(); // Инициализация ChromeDriver
        driver.get("https://www.mts.by/"); // Открытие страницы
        mtsPage = new MtsPage(driver);
        mtsPage.acceptCookies(); // Принять куки, если это необходимо
    }

    @Test
    public void testServicesPayment() {
        // Заполнение полей для "Услуги связи"
        String phoneNumber = "375297777777"; // Номер телефона
        String expectedAmount = "100.00 BYN"; // Ожидаемая сумма

        // Заполнение поля номера телефона
        mtsPage.fillPhoneNumber(phoneNumber); // Метод для заполнения номера телефона
        mtsPage.clickContinueButton(); // Нажимаем кнопку "Продолжить"

        // Ожидание появления окна с результатами
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".payment-page__order-description"))); // Ожидание элемента с описанием заказа

        // Проверка корректности отображения суммы
        WebElement amountElement = driver.findElement(By.cssSelector(".pay-description__cost span"));
        assertEquals(expectedAmount, amountElement.getText(), "Сумма отображается некорректно.");

        // Проверка номера телефона
        WebElement phoneElement = driver.findElement(By.cssSelector(".pay-description__text span"));
        assertEquals(phoneNumber, phoneElement.getText(), "Номер телефона отображается некорректно.");

        // Проверка надписей в незаполненных полях для ввода реквизитов карты
        assertEquals("Номер карты", driver.findElement(By.cssSelector("input#cc-number + label")).getText(), "Placeholder для номера карты неверен.");
        assertEquals("Срок действия", driver.findElement(By.cssSelector("input[formcontrolname='expirationDate'] + label")).getText(), "Placeholder для срока действия неверен.");
        assertEquals("CVC", driver.findElement(By.cssSelector("input[formcontrolname='cvc'] + label")).getText(), "Placeholder для CVV неверен.");
        assertEquals("Имя держателя (как на карте)", driver.findElement(By.cssSelector("input[formcontrolname='holder'] + label")).getText(), "Placeholder для имени держателя неверен.");

        // Проверка наличия иконок платёжных систем
        assertTrue(driver.findElement(By.cssSelector("img[src*='visa-system.svg']")).isDisplayed(), "Иконка Visa не отображается.");
        assertTrue(driver.findElement(By.cssSelector("img[src*='mastercard-system.svg']")).isDisplayed(), "Иконка Mastercard не отображается.");

        // Проверка кнопки "Оплатить"
        WebElement payButton = driver.findElement(By.cssSelector("button[type='submit']"));
        assertTrue(payButton.isDisplayed(), "Кнопка 'Оплатить' не отображается.");
    }

    @AfterEach
    public void tearDown() {
        driver.quit(); // Закрытие драйвера после теста
    }
}