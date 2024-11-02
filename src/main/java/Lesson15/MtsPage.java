package Lesson15;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MtsPage {
    private WebDriver driver;

    public MtsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookies() {
        // Принять куки, если появляется соответствующий элемент
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
        // Используем XPath для получения заголовка блока
        WebElement titleElement = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2"));
        return titleElement.getText();
    }

    public boolean arePaymentSystemLogosVisible() {
        // Проверка наличия логотипов платёжных систем с использованием XPath
        return driver.findElement(By.xpath("//*[@id=\"pay-section\"]")).isDisplayed();
    }

    public void clickMoreInfoLink() {
        // Используем XPath для нахождения и клика по ссылке "Подробнее о сервисе"
        WebElement moreInfoLink = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a"));
        moreInfoLink.click();
    }

    public void fillServiceFields(String phoneNumber) {
        // Заполнение полей с использованием XPath
        WebElement serviceField = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button/span[1]"));
        serviceField.sendKeys("Услуги связи");

        WebElement phoneField = driver.findElement(By.xpath("//*[@id=\"connection-phone\"]"));
        phoneField.sendKeys(phoneNumber);

        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        continueButton.click();
    }
}