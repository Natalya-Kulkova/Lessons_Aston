package Lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

public class MtsPageTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Настройка WebDriverManager для автоматической загрузки драйвера
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://mts.by"); // URL сайта
    }
    @BeforeEach
    public void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree"))); // Замените селектор на правильный
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
            }
        } catch (Exception e) {
            System.out.println("Кнопка принятия куки не найдена или уже принята.");
        }
    }
    @Test
    public void testBlockTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Ожидаем, пока заголовок станет видимым
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pay__wrapper']/h2")));

        // Ожидаемое значение заголовка
        String expectedTitle = "Онлайн пополнение без комиссии"; // Заменяем перенос строки на пробел
        String actualTitle = titleElement.getText().replace("\n", " "); // Заменяем перенос строки на пробел

        // Проверяем, что заголовок соответствует ожидаемому значению
        assertEquals(expectedTitle, actualTitle, "Заголовок блока не соответствует ожидаемому значению.");
    }

    @Test
    public void testPaymentSystemLogos() {
        // Проверка наличия логотипов платёжных систем
        List<WebElement> logos = driver.findElements(By.cssSelector(".pay__partners img"));

        // Проверяем, что логотипы присутствуют
        assertTrue(logos.size() > 0, "Логотипы платёжных систем не найдены.");

        // Проверяем наличие конкретных логотипов
        boolean hasVisa = logos.stream().anyMatch(logo -> logo.getAttribute("alt").equals("Visa"));
        boolean hasVerified = logos.stream().anyMatch(logo -> logo.getAttribute("alt").equals("Verified By Visa"));
        boolean hasMasterCard = logos.stream().anyMatch(logo -> logo.getAttribute("alt").equals("MasterCard"));
        boolean hasSecureCode = logos.stream().anyMatch(logo -> logo.getAttribute("alt").equals("MasterCard Secure Code"));
        boolean hasBelkart = logos.stream().anyMatch(logo -> logo.getAttribute("alt").equals("Белкарт"));

        // Ассерты для проверки наличия логотипов
        assertTrue(hasVisa, "Логотип Visa не найден.");
        assertTrue(hasVerified, "Логотип Verified By Visa не найден.");
        assertTrue(hasMasterCard, "Логотип MasterCard не найден.");
        assertTrue(hasSecureCode, "Логотип MasterCard Secure Code не найден.");
        assertTrue(hasBelkart, "Логотип Белкарт не найден.");
    }

    @Test
    public void testMoreAboutServiceLink() {
        // Находим ссылку "Подробнее о сервисе"
        WebElement moreAboutServiceLink = driver.findElement(By.linkText("Подробнее о сервисе"));

        // Получаем URL ссылки
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String actualUrl = moreAboutServiceLink.getAttribute("href");

        // Проверяем, что URL соответствует ожидаемому
        assertEquals(expectedUrl, actualUrl, "URL ссылки не соответствует ожидаемому.");

        // Кликаем по ссылке
        moreAboutServiceLink.click();

        // Проверяем, что мы перешли на правильную страницу
        String currentUrl = driver.getCurrentUrl(); // Получаем текущий URL
        assertEquals(expectedUrl, currentUrl, "Не удалось перейти на страницу 'Подробнее о сервисе'.");

        // Возвращаемся на предыдущую страницу
        driver.navigate().back();

        // Проверяем, что мы вернулись на предыдущую страницу
        String previousUrl = driver.getCurrentUrl(); // Получаем текущий URL после возврата
        String expectedPreviousUrl = "https://www.mts.by/"; // Ожидаемый URL предыдущей страницы
        assertEquals(expectedPreviousUrl, previousUrl, "Не удалось вернуться на предыдущую страницу.");
    }

    @Test
    public void testFillFieldsAndContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Выбор услуги из выпадающего списка
        WebElement serviceSelect = wait.until(ExpectedConditions.elementToBeClickable(By.id("pay")));
        serviceSelect.click();

        // Заполнение полей
        WebElement phoneInput = driver.findElement(By.id("connection-phone"));
        phoneInput.sendKeys("297777777");

        WebElement sumInput = driver.findElement(By.id("connection-sum"));
        sumInput.sendKeys("100"); // Пример суммы

        WebElement emailInput = driver.findElement(By.id("connection-email"));
        emailInput.sendKeys("test@example.com"); // Пример email

        // Нажатие на кнопку "Продолжить"
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        continueButton.click();

        // Ожидание и проверка, что iframe с классом .bepaid-iframe появился на странице
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bepaid-iframe")));

        // Проверка, что iframe с классом .bepaid-iframe присутствует на странице
        WebElement iframeElement = driver.findElement(By.cssSelector(".bepaid-iframe"));
        assertTrue(iframeElement.isDisplayed(), "Окно для оплаты не отображается после нажатия кнопки 'Продолжить'.");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}