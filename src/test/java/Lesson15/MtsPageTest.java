package Lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class MtsPageTest {
    private static WebDriver driver;
    private MtsPage mtsPage;

    @BeforeAll
    public static void setUp() {
        // Настройка WebDriverManager для автоматической загрузки драйвера
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://mts.by"); // URL сайта
    }

    @BeforeEach
    public void acceptCookies() {
        // Принять куки, если появляется соответствующий элемент
        try {
            // Замените XPath на актуальный для кнопки принятия куки
            WebElement acceptCookiesButton = driver.findElement(By.xpath("//*[@id='cookie-agree']"));
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException e) {
            // Если элемент не найден, куки уже приняты или не требуется
        }
        mtsPage = new MtsPage(driver);
    }

    @Test
    public void testBlockTitle() {
        String expectedTitle = "Онлайн пополнение без комиссии"; // Замените на актуальное название блока
        String actualTitle = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2")).getText();
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testPaymentSystemLogos() {
        // Проверка наличия логотипов платёжных систем
        assertTrue(driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]")).isDisplayed(), "Логотипы платёжных систем не отображаются.");
    }

    @Test
    public void testMoreInfoLink() {
        WebElement moreInfoLink = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a"));
        moreInfoLink.click();
        // Добавьте проверки, чтобы убедиться, что переход по ссылке работает
        assertTrue(driver.getCurrentUrl().contains("expected_url_part"), "Переход по ссылке не сработал.");
    }

    @Test
    public void testContinueButton() {
        mtsPage.fillServiceFields("297777777");
        // Добавьте проверки, чтобы убедиться, что кнопка "Продолжить" работает
        // Например, проверка, что после нажатия кнопки появляется ожидаемый элемент
        assertTrue(driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button")).isDisplayed(), "Сообщение об успешном продолжении не отображается.");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}