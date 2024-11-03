package Lesson16;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Конструктор
    public MtsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Инициализация WebDriverWait
    }

    // Метод для принятия куки
    public void acceptCookies() {
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree")));
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Кнопка принятия куки не найдена или уже принята.");
        }
    }

    // Метод для получения заголовка блока
    public String getBlockTitle() {
        return driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение без комиссии')]")).getText();
    }

    // Метод для проверки видимости логотипов платёжных систем
    public boolean arePaymentSystemLogosVisible() {
        return driver.findElement(By.xpath("//div[@class='payment-logos']")).isDisplayed();
    }

    // Метод для нажатия на ссылку "Подробнее о сервисе"
    public void clickMoreInfoLink() {
        driver.findElement(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")).click();
    }

    // Метод для заполнения полей услуги
    public void fillServiceFields(String phoneNumber) {
        // Ожидание появления поля для услуги
        WebElement serviceField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='service']")));
        serviceField.sendKeys("Услуги связи");

        // Ожидание появления поля для телефона
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='phone']")));
        phoneField.sendKeys(phoneNumber);

        // Ожидание кнопки "Продолжить" и нажатие на нее
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='pay-connection']/button")));
        continueButton.click();
    }

    // Метод для заполнения поля номера телефона
    public void fillPhoneNumber(String phoneNumber) {
        // Находим поле ввода номера телефона по его ID
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone-input"))); // Замените на правильный ID
        phoneField.clear(); // Очищаем поле перед вводом
        phoneField.sendKeys(phoneNumber); // Вводим номер телефона
    }

    // Метод для нажатия кнопки "Продолжить"
    public void clickContinueButton() {
        // Находим кнопку "Продолжить" по ее ID
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue-button"))); // Замените на правильный ID
        continueButton.click(); // Нажимаем на кнопку
    }

    // Метод для получения плейсхолдера поля
    public String getFieldPlaceholder(String fieldName) {
        // Находим поле по имени и возвращаем его плейсхолдер
        return driver.findElement(By.name(fieldName)).getAttribute("placeholder");
    }

    // Метод для проверки деталей платежа
    public void checkPaymentDetails(String expectedAmount, String expectedPhoneNumber) {
        // Проверка суммы на кнопке
        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), '" + expectedAmount + "')]")));
        String buttonText = continueButton.getText();
        assertTrue(buttonText.contains(expectedAmount), "Сумма на кнопке не соответствует ожидаемой.");

        // Проверка номера телефона
        WebElement phoneNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='connection-phone']")));
        assertEquals(expectedPhoneNumber, phoneNumberElement.getAttribute("value"), "Номер телефона не соответствует ожидаемому.");

        // Проверка наличия иконок платёжных систем
        assertTrue(driver.findElement(By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/div[2]")).isDisplayed(), "Иконки платёжных систем не отображаются.");
    }
}