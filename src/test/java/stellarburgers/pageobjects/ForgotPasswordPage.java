package stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;

public class ForgotPasswordPage {

    private final SelenideElement emailInputField = $x(".//label[text()='Имя']/../input");
    private final SelenideElement restorePasswordButton = $x(".//button[text()='Восстановить']");
    private final SelenideElement signInLink = $x(".//a[text()='Войти']");

    @Step("Ввод пароля")
    public void setEmailInputField(String email) {
        emailInputField.setValue(email);
    }

    @Step("Клик по кнопке Восстановить")
    public void clickRestorePasswordButton() {
        restorePasswordButton.click();
    }

    @Step("Клик по ссылке Войти")
    public void clickSignInLink() {
        signInLink.click();
    }
}
