package stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;


public class RegisterPage {

    private final SelenideElement nameInputField = $x(".//label[text()='Имя']/../input");
    private final SelenideElement emailIInputField = $x(".//label[text()='Email']/../input");

    private final SelenideElement passwordInputField = $x(".//label[text()='Пароль']/../input");
    private final SelenideElement passwordErrorText = $x(".//p[text()='Некорректный пароль']");

    private final SelenideElement registerButton = $x(".//button[text()='Зарегистрироваться']");

    private final SelenideElement signInLink = $x(".//a[text()='Войти']");

    @Step("Заполнение формы регистрации")
    public void setRegisterForm(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    @Step("Нажатие кнопки Зарегистрироваться")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Отображение ошибки ввода пароля")
    public boolean isVisiblePasswordErrorText() {
        return passwordErrorText.shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Клик по кнопке Войти")
    public void clickSignInLink() {
        signInLink.click();
    }

    private void setName(String name) {
        nameInputField.setValue(name);
    }

    private void setEmail(String email) {
        emailIInputField.setValue(email);
    }

    private void setPassword(String password) {
        passwordInputField.setValue(password);
    }



}
