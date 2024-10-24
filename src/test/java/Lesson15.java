package Lesson15;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Lesson15 {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Путь к драйверу Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\60093489\\Downloads\\chromedriver-win64 (2)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://mts.by");
    }

    @Test
    public void testOnlineReplenishmentBlock() {
        // 1. Проверить название указанного блока
        WebElement blockTitle = driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение без комиссии')]"));
        assertNotNull(blockTitle, "Блок 'Онлайн пополнение без комиссии' не найден");
        assertEquals("Онлайн пополнение без комиссии", blockTitle.getText());

        // 2. Проверить наличие логотипов платежных систем
        List<WebElement> paymentLogos = driver.findElements(By.cssSelector(".payment-logos img"));
        assertFalse(paymentLogos.isEmpty(), "Логотипы платежных систем не найдены");

        // 3. Проверить работу ссылки "Подробнее о сервисе"
        WebElement moreInfoLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        assertNotNull(moreInfoLink, "Ссылка 'Подробнее о сервисе' не найдена");
        moreInfoLink.click();
        assertTrue(driver.getCurrentUrl().contains("service-info"), "Ссылка 'Подробнее о сервисе' не работает");

        // Вернуться на главную страницу
        driver.navigate().back();
    }

    @Test
    public void testContinueButtonFunctionality() {
        // 4. Заполнить поля и проверить работу кнопки "Продолжить"
        WebElement serviceOption = driver.findElement(By.xpath("//input[@value='Услуги связи']"));
        serviceOption.click();

        WebElement phoneNumberField = driver.findElement(By.id("phoneNumber"));
        phoneNumberField.sendKeys("297777777");

        WebElement continueButton = driver.findElement(By.xpath("//button[contains(text(), 'Продолжить')]"));
        continueButton.click();

        // Проверка, что переход на следующую страницу успешен
        assertTrue(driver.getCurrentUrl().contains("next-step"), "Кнопка 'Продолжить' не работает");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}