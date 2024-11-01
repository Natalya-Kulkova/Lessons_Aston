package Lesson16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;

public class MtsPage {
    private WebDriver driver;

    public MtsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookies() {
        try {
            WebElement acceptCookiesButton = driver.findElement(By.xpath("//*[@id='cookie-agree']"));
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException e) {
            // Если элемент не найден, куки уже приняты или не требуется
        }
    }

    public String getBlockTitle() {
        return driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение без комиссии')]")).getText();
    }

    public boolean arePaymentSystemLogosVisible() {
        return driver.findElement(By.xpath("//div[@class='payment-logos']")).isDisplayed();
    }

    public void clickMoreInfoLink() {
        driver.findElement(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")).click();
    }

    public void fillServiceFields(String phoneNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ожидание до 10 секунд

        // Ожидание появления поля для услуги
        WebElement serviceField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='service']")));
        serviceField.sendKeys("Услуги связи");

        // Ожидание появления поля для телефона
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='phone']")));
        phoneField.sendKeys(phoneNumber);

        // Ожидание кнопки "Продолжить" и нажатие на нее
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pay-connection\"]/button")));
        continueButton.click();
    }

    public String getFieldPlaceholder(String fieldName) {
        return driver.findElement(By.xpath("//input[@name='pay-connection" + fieldName + "']")).getAttribute("placeholder");
    }

    public void checkPaymentDetails(String expectedAmount, String expectedPhoneNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ожидание до 10 секунд

        // Проверка суммы на кнопке
        WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/button")));
        String buttonText = continueButton.getText();
        assertTrue(buttonText.contains(expectedAmount), "Сумма на кнопке не соответствует ожидаемой.");

        // Проверка номера телефона
        WebElement phoneNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='connection-phone']")));
        assertEquals(expectedPhoneNumber, phoneNumberElement.getText(), "Номер телефона не соответствует ожидаемому.");

        // Проверка наличия иконок платёжных систем
        assertTrue(driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]")).isDisplayed(), "Иконки платёжных систем не отображаются.");
    }
}