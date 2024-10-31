package Lesson15;

import Lesson15.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MTSTests extends BaseTest {
    private MainPage mainPage;

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();  // принимаем куки перед каждым тестом
    }

    @Test
    @DisplayName("Проверка заголовка блока онлайн пополнения")
    public void testBlockTitle() {
        assertEquals("Онлайн пополнение без комиссии",
                mainPage.getBlockTitle(),
                "Неверный заголовок блока");
    }

    @Test
    @DisplayName("Проверка отображения логотипов платежных систем")
    public void testPaymentLogos() {
        assertTrue(mainPage.arePaymentLogosDisplayed(),
                "Логотипы платежных систем не отображаются");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testMoreInfoLink() {
        mainPage.clickMoreInfoLink();
        assertTrue(mainPage.getCurrentUrl().contains("service-info"),
                "Некорректный переход по ссылке 'Подробнее'");
    }

    @Test
    @DisplayName("Проверка функциональности формы пополнения")
    public void testPaymentForm() {
        mainPage.selectServiceOption();
        mainPage.enterPhoneNumber("297777777");
        mainPage.clickContinueButton();

        assertTrue(mainPage.getCurrentUrl().contains("next-step"),
                "Некорректный переход после заполнения формы");
    }
}