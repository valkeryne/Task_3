package stellarburgers.pageobjects;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    private final String visibleCheckerJsCode = String.join("\n",
            "var element = arguments[0];",
            "var container = document.querySelector(\"[class*='BurgerIngredients_ingredients__menuContainer']\");",
            "if (!container) return false;",
            "var elementRect = element.getBoundingClientRect();",
            "var containerRect = container.getBoundingClientRect();",
            "return (elementRect.top >= containerRect.top && elementRect.bottom <= containerRect.bottom);"
    );

    private final SelenideElement signInButton = $x(".//button[text()='Войти в аккаунт']");
    private final SelenideElement accountLink = $x(".//a[p[text()='Личный Кабинет']]");
    private final SelenideElement createOrderButton = $x(".//button[text()='Оформить заказ']");
    private final SelenideElement bunTab = $x(".//span[text()='Булки']/..");
    private final SelenideElement sauceTab = $x(".//span[text()='Соусы']/..");
    private final SelenideElement fillingTab = $x(".//span[text()='Начинки']/..");
    private final SelenideElement bunBlock = $x(".//h2[text()='Булки']");
    private final SelenideElement sauceBlock = $x(".//h2[text()='Соусы']");
    private final SelenideElement fillingBlock = $x(".//h2[text()='Начинки']");


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

    @Step("Клик по табу Булки")
    public void clickBunTab() {
        bunTab.click();
    }

    @Step("Клик по табу Соусы")
    public void clickSauceTab() {
        sauceTab.click();
    }

    @Step("Клик по табу Начинки")
    public void clickFillingTab() {
        fillingTab.click();
    }

    @Step("Таб Булки активен")
    public boolean isActiveBunTab() {
        String bunTabClasses =  bunTab.getAttribute("class");
        return bunTabClasses != null && bunTabClasses.contains("tab_tab_type_current");
    }

    @Step("Таб Соусы активен")
    public boolean isActiveSauceTab() {
        String sauceTabClasses =  sauceTab.getAttribute("class");
        return sauceTabClasses != null && sauceTabClasses.contains("tab_tab_type_current");
    }

    @Step("Таб Начинки активен")
    public boolean isActiveFillingTab() {
        String fillingTabClasses =  fillingTab.getAttribute("class");
        return fillingTabClasses != null && fillingTabClasses.contains("tab_tab_type_current");
    }

    @Step("Блок с булками виден на экране")
    public boolean isVisibleBunBlock() {
        return isVisibleOnScrollConstructor(bunBlock);
    }

    @Step("Блок с соусами виден на экране")
    public boolean isVisibleSauceBlock() {
        return isVisibleOnScrollConstructor(sauceBlock);
    }

    @Step("Блок с начинками виден на экране")
    public boolean isVisibleFillingBlock() {
        return isVisibleOnScrollConstructor(fillingBlock);
    }

    private boolean isVisibleOnScrollConstructor(SelenideElement element) {
        return Boolean.TRUE.equals(Selenide.executeJavaScript(visibleCheckerJsCode, element));
    }
}
