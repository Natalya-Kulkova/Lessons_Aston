package Lesson16;

import Lesson16.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class MTSPaymentTests extends BaseTest {
    private MainPage mainPage;

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();
    }

    @Test
    @DisplayName("Проверка валидации пустого номера телефона")
    public void testEmptyPhoneValidation() {
        mainPage.enterPhoneNumber("");
        mainPage.enterAmount("10");
        mainPage.clickSubmit();

        assertFalse(mainPage.isPhoneFieldValid(), "Поле телефона должно быть невалидным");
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "abc", "+375", "375291234567890"})
    @DisplayName("Проверка валидации некорректного номера телефона")
    public void testInvalidPhoneValidation(String phone) {
        mainPage.enterPhoneNumber(phone);
        mainPage.enterAmount("10");
        mainPage.clickSubmit();

        assertFalse(mainPage.isPhoneFieldValid(),
                "Поле телефона должно быть невалидным для значения: " + phone);
    }

    @Test
    @DisplayName("Проверка валидации пустой суммы")
    public void testEmptyAmountValidation() {
        mainPage.enterPhoneNumber("375291234567");
        mainPage.enterAmount("");
        mainPage.clickSubmit();

        assertFalse(mainPage.isAmountFieldValid(), "Поле суммы должно быть невалидным");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "1001", "abc"})
    @DisplayName("Проверка валидации некорректной суммы")
    public void testInvalidAmountValidation(String amount) {
        mainPage.enterPhoneNumber("375291234567");
        mainPage.enterAmount(amount);
        mainPage.clickSubmit();

        assertFalse(mainPage.isAmountFieldValid(),
                "Поле суммы должно быть невалидным для значения: " + amount);
    }

    @Test
    @DisplayName("Проверка успешного заполнения формы")
    public void testValidFormSubmission() {
        mainPage.enterPhoneNumber("375291234567");
        mainPage.enterAmount("10");
        mainPage.clickSubmit();

        assertTrue(mainPage.isPhoneFieldValid(), "Поле телефона должно быть валидным");
        assertTrue(mainPage.isAmountFieldValid(), "Поле суммы должно быть валидным");
        assertTrue(mainPage.getErrorMessage().isEmpty(), "Не должно быть сообщений об ошибках");
    }
}
