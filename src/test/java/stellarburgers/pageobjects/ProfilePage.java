package stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    private final SelenideElement constructorLink = $x(".//a[p[text()='Конструктор']]");
    private final SelenideElement signOutButton = $x(".//button[text()='Выход']");
    private final SelenideElement brandLink = $x(".//div[contains(@class, 'AppHeader_header__logo')]/a");

    @Step("Клик по ссылке конструктора")
    public void clickConstructorLink() {
        constructorLink.click();
    }

    @Step("Клик по кнопке Выход")
    public void clickSignOutButton() {
        signOutButton.click();
    }

    @Step("Отображение кнопки Выйти")
    public boolean isVisibleSignOutButton() {
        return signOutButton.shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Клик по логотипу stellar burgers")
    public void clickBrandLink() {
        brandLink.click();
    }

}
