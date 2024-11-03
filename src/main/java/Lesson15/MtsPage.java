package Lesson15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MtsPage {
    private WebDriver driver;

    // Конструктор
    public MtsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Элементы страницы
    private By serviceSelect = By.id("pay");
    private By phoneInput = By.id("connection-phone");
    private By sumInput = By.id("connection-sum");
    private By emailInput = By.id("connection-email");
    private By continueButton = By.cssSelector("button[type='submit']");
    private By iframe = By.cssSelector(".bepaid-iframe");

    // Методы для взаимодействия с элементами
    public void selectService(String service) {
        WebElement selectElement = driver.findElement(serviceSelect);
        selectElement.click();
        // Здесь можно добавить логику для выбора конкретной услуги из выпадающего списка
    }

    public void fillPhone(String phone) {
        WebElement phoneField = driver.findElement(phoneInput);
        phoneField.sendKeys(phone);
    }

    public void fillSum(String sum) {
        WebElement sumField = driver.findElement(sumInput);
        sumField.sendKeys(sum);
    }

    public void fillEmail(String email) {
        WebElement emailField = driver.findElement(emailInput);
        emailField.sendKeys(email);
    }

    public void clickContinue() {
        WebElement continueBtn = driver.findElement(continueButton);
        continueBtn.click();
    }

    public boolean isIframeDisplayed() {
        try {
            return driver.findElement(iframe).isDisplayed();
        } catch (Exception e) {
            return false; // Если элемент не найден, возвращаем false
        }
    }
}