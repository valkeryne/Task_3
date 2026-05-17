package stellarburgers.tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import stellarburgers.models.User;
import stellarburgers.pageobjects.HomePage;
import stellarburgers.pageobjects.LoginPage;
import stellarburgers.pageobjects.ProfilePage;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileTest extends BaseTest {

    private HomePage homePage;
    private ProfilePage profilePage;
    private LoginPage loginPage;
    private String accessToken;

    @Before
    public void createRegisterAndAuthUser() {
        User user = generateUser();
        Response createUserResponse = userApi.register(user);
        accessToken = createUserResponse.path("accessToken");
        String refreshToken = createUserResponse.path("refreshToken");
        homePage = Selenide.open("/", HomePage.class);
        profilePage = Selenide.page(ProfilePage.class);
        loginPage = Selenide.page(LoginPage.class);
        Selenide.localStorage().setItem("accessToken", "Bearer " + accessToken);
        Selenide.localStorage().setItem("refreshToken", refreshToken);
        Selenide.refresh();
    }

    @Test
    @DisplayName("Переход по клику Личный кабинет")
    @Description("Проверка перехода в личный кабинет из главной страницы по клику на  Личный кабинет")
    public void homeProfileLink() {
        homePage.clickAccountLink();
        assertThat(profilePage.isVisibleSignOutButton())
                .as("Открыта страница профиля")
                .isTrue();
    }

    @Test
    @DisplayName("Переход в конструктор по кнопке конструктора")
    @Description("Проверка перехода из личного кабинета в конструктор по клину на Конструктор")
    public void profileConstructorLink() {
        homePage.clickAccountLink();
        profilePage.isVisibleSignOutButton();
        profilePage.clickConstructorLink();
        assertThat(homePage.isVisibleCreateOrderButton())
                .as("Должна отображаться кнопка создания заказа")
                .isTrue();
    }

    @Test
    @DisplayName("Переход в конструктор по логотипу stellar burgers")
    @Description("Проверка перехода из личного кабинета в конструктор по клину на Конструктор")
    public void profileBrandLink() {
        homePage.clickAccountLink();
        profilePage.isVisibleSignOutButton();
        profilePage.clickBrandLink();
        assertThat(homePage.isVisibleCreateOrderButton())
                .as("Должна отображаться кнопка создания заказа")
                .isTrue();
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта")
    @Description("По кнопке Выйти пользователя должно перенаправить на страницу Входа")
    public void signOut() {
        homePage.clickAccountLink();
        profilePage.isVisibleSignOutButton();
        profilePage.clickSignOutButton();
        assertThat(loginPage.isDisplayedLoginButton())
                .as("Кнопка Войти должна быть видимой")
                .isTrue();
    }

    @After
    public void cleanup() {
        userApi.deleteUser(accessToken);
    }
}
