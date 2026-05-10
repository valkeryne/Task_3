package stellarburgers.pageobjects;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    private final SelenideElement signInButton = $x(".//button[text()='Войти в аккаунт']");
    private final SelenideElement accountLink = $x(".//a[p[text()='Личный Кабинет']]");
    private final SelenideElement createOrderButton = $x(".//button[text()='Оформить заказ']");

    @Step("Клик по кнопке Войти в аккаунт")
    public void clickSignInButton() {
        signInButton.click();
    }

    @Step("Отображение кнопки Войти в аккаунт")
    public boolean isVisibleSignInButton() {
        return signInButton.shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Клик по ссылке Личный кабинет")
    public void clickAccountLink() {
        accountLink.click();
    }

    @Step("Отображение кнопки Оформить заказ")
    public boolean isVisibleCreateOrderButton() {
        return createOrderButton.shouldBe(Condition.visible).isDisplayed();
    }
}
