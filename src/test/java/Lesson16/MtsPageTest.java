package Lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class MtsPageTest {
    private static WebDriver driver;
    private MtsPage mtsPage;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://mts.by"); // URL сайта
    }

    @BeforeEach
    public void prepare() {
        mtsPage = new MtsPage(driver);
        mtsPage.acceptCookies(); // Принять куки перед каждым тестом
    }

    @Test
    public void testFieldPlaceholders() {
        // Проверка надписей в незаполненных полях для каждого варианта оплаты
        assertEquals("Введите номер телефона", mtsPage.getFieldPlaceholder("Номер телефона"), "Неправильная надпись в поле номера телефона.");
        assertEquals("Введите сумму", mtsPage.getFieldPlaceholder("Сумма"), "Неправильная надпись в поле суммы.");
        assertEquals("Введите реквизиты карты", mtsPage.getFieldPlaceholder("Номер счета на 44"), "Неправильная надпись в поле реквизитов карты.");
        assertEquals("Введите задолженность", mtsPage.getFieldPlaceholder("Номер счета на 2073"), "Неправильная надпись в поле задолженности.");
    }

    @Test
    public void testContinueButton() {
        String phoneNumber = "297777777";
        String expectedAmount = "100";
        mtsPage.fillServiceFields(phoneNumber);

        // Проверка деталей после нажатия кнопки "Продолжить"
        mtsPage.checkPaymentDetails(expectedAmount, phoneNumber);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}