package Lesson15.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final String URL = "https://www.mts.by";

    @FindBy(className = "pay")
    private WebElement blockTitle;

    @FindBy(className = "pay_partners")
    private List<WebElement> paymentLogos;

    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a")
    private WebElement moreInfoLink;

    @FindBy(linkText = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/")
    private WebElement serviceOption;

    @FindBy(id = "connection-phone")
    private WebElement phoneNumberField;

    @FindBy(className = "button button_default")
    private WebElement continueButton;

    @FindBy(id = "cookie-agree")
    private WebElement cookieAcceptButton;

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
            wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
            wait.until(ExpectedConditions.invisibilityOf(cookieAcceptButton));
        } catch (Exception e) {
            // Cookies already accepted or banner not present
        }
    }

    public String getBlockTitle() {
        return wait.until(ExpectedConditions.visibilityOf(blockTitle)).getText();
    }

    public boolean arePaymentLogosDisplayed() {
        return !paymentLogos.isEmpty() &&
                paymentLogos.stream().allMatch(WebElement::isDisplayed);
    }

    public void clickMoreInfoLink() {
        wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink)).click();
    }

    public void selectServiceOption() {
        wait.until(ExpectedConditions.elementToBeClickable(serviceOption)).click();
    }

    public void enterPhoneNumber(String number) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(phoneNumberField));
        input.clear();
        input.sendKeys(number);
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void navigateBack() {
        driver.navigate().back();
    }
}