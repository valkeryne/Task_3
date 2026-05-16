package stellarburgers.tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.pageobjects.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class ConstructorTest extends BaseTest {

    private HomePage homePage;

    @Before
    public void openHomePage() {
        homePage = Selenide.open("/", HomePage.class);
    }

    @Test
    @DisplayName("Нажатие на таб Булки")
    @Description("Блок Булки должен быть внутри скролла, таб должен иметь класс tab_tab_type_current")
    public void bunTab() {
        homePage.clickSauceTab();
        assertThat(homePage.isActiveBunTab())
                .as("Таб с булками не должен иметь класс tab_tab_type_current")
                .isFalse();
        assertThat(homePage.isVisibleBunBlock())
                .as("Блок с булками не должен быть видимым")
                .isFalse();

        homePage.clickBunTab();
        assertThat(homePage.isActiveBunTab())
                .as("Таб с булками должен иметь класс tab_tab_type_current")
                .isTrue();
        assertThat(homePage.isVisibleBunBlock())
                .as("Блок с булками должен быть видимым")
                .isTrue();
    }

    @Test
    @DisplayName("Нажатие на таб Соусы")
    @Description("Блок Соусы должен быть внутри скролла, таб должен иметь класс tab_tab_type_current")
    public void sauceTab() {
        homePage.clickSauceTab();
        assertThat(homePage.isActiveSauceTab())
                .as("Таб с соусами должен иметь класс tab_tab_type_current")
                .isTrue();
        assertThat(homePage.isVisibleSauceBlock())
                .as("Блок с соусами должен быть видимым")
                .isTrue();
    }

    @Test
    @DisplayName("Нажатие на таб Начинки")
    @Description("Блок Начинки должен быть внутри скролла, таб должен иметь класс tab_tab_type_current")
    public void fillingTab() {
        homePage.clickFillingTab();
        assertThat(homePage.isActiveFillingTab())
                .as("Таб с начинками должен иметь класс tab_tab_type_current")
                .isTrue();
        assertThat(homePage.isVisibleFillingBlock())
                .as("Блок с начинками должен быть видимым")
                .isTrue();
    }
}
