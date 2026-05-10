package stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement emailInputField = $x(".//label[text()='Email']/../input");
    private final SelenideElement passwordInputField = $x(".//label[text()='Пароль']/../input");

    private final SelenideElement loginButton = $x(".//button[text()='Войти']");

    @Step("Заполнения формы логина")
    public void setLoginForm(String email, String password) {
        setEmailInputField(email);
        setPasswordInputField(password);
    }

    @Step("Клик по кнопке Войти")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Отображение кнопки логина")
    public boolean isDisplayedLoginButton() {
        return loginButton.shouldBe(Condition.visible).isDisplayed();
    }

    private void setEmailInputField(String email) {
        emailInputField.setValue(email);
    }

    private void setPasswordInputField(String password) {
        passwordInputField.setValue(password);
    }
}
