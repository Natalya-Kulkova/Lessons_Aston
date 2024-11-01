package Lesson16.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final String URL = "https://www.mts.by";

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//input[@name='amount']")
    private WebElement amountInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class, 'error-message')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[contains(@class, 'cookie-button') or contains(@class, 'accept-cookies')]")
    private WebElement cookieButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void acceptCookies() {
        try {
            Thread.sleep(1000);
            if (cookieButton != null && cookieButton.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
                System.out.println("Cookies accepted successfully");
                wait.until(ExpectedConditions.invisibilityOf(cookieButton));
            }
        } catch (Exception e) {
            System.out.println("Cookies already accepted or banner not found");
        }
    }

    public void enterPhoneNumber(String phone) {
        wait.until(ExpectedConditions.elementToBeClickable(phoneInput)).clear();
        phoneInput.sendKeys(phone);
        System.out.println("Entered phone number: " + phone);
    }

    public void enterAmount(String amount) {
        wait.until(ExpectedConditions.elementToBeClickable(amountInput)).clear();
        amountInput.sendKeys(amount);
        System.out.println("Entered amount: " + amount);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        System.out.println("Clicked submit button");
    }

    public String getErrorMessage() {
        try {
            String message = wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText().trim();
            System.out.println("Error message: " + message);
            return message;
        } catch (Exception e) {
            System.out.println("No error message found");
            return "";
        }
    }

    public boolean isPhoneFieldValid() {
        try {
            return !phoneInput.getAttribute("class").contains("error");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAmountFieldValid() {
        try {
            return !amountInput.getAttribute("class").contains("error");
        } catch (Exception e) {
            return false;
        }
    }
}