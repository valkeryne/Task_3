package stellarburgers.tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import io.restassured.response.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import stellarburgers.models.User;
import stellarburgers.pageobjects.ForgotPasswordPage;
import stellarburgers.pageobjects.HomePage;
import stellarburgers.pageobjects.LoginPage;
import stellarburgers.pageobjects.RegisterPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private User user;
    private String accessToken;

    @Before
    public void createAndRegisterUser() {
        user = generateUser();
        Response createUserResponse = userApi.register(user);
        accessToken = createUserResponse.path("accessToken");
        homePage = Selenide.open("/", HomePage.class);
        loginPage = Selenide.page(LoginPage.class);
    }

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт")
    @Description("Проверка авторизации по кнопке Войти в аккаунт на главной странице")
    public void homeSignInButton() {
        homePage.clickSignInButton();
        assertThat(loginPage.isDisplayedLoginButton())
                .as("Должна появиться страница логина")
                .isTrue();

        loginPage.setLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();

        assertThat(homePage.isVisibleCreateOrderButton())
                .as("На главной странице должна появиться кнопки Оформить заказ")
                .isTrue();
    }

    @Test
    @DisplayName("Вход по ссылке Личный кабинет")
    @Description("Проверка авторизации по ссылке Личный кабинет на главной странице")
    public void homeAccountLinkButton() {
        homePage.clickAccountLink();
        assertThat(loginPage.isDisplayedLoginButton())
                .as("Должна появиться страница логина")
                .isTrue();
        loginPage.setLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();

        assertThat(homePage.isVisibleCreateOrderButton())
                .as("На главной странице должна появиться кнопка Оформить заказ")
                .isTrue();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка авторизации при переходе из страницы регистрации")
    public void registerLink() {
        RegisterPage registerPage = Selenide.open("/register", RegisterPage.class);
        registerPage.clickSignInLink();
        assertThat(loginPage.isDisplayedLoginButton())
                .as("Должна появиться страница логина")
                .isTrue();
        loginPage.setLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        assertThat(homePage.isVisibleCreateOrderButton())
                .as("На главной странице должна появиться кнопка Оформить заказ")
                .isTrue();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка авторизации пользователя при переходе из страницы восстановления пароля")
    public void forgotPasswordLink() {
        ForgotPasswordPage forgotPasswordPage = Selenide.open("/forgot-password", ForgotPasswordPage.class);
        forgotPasswordPage.clickSignInLink();
        assertThat(loginPage.isDisplayedLoginButton())
                .as("Должна появиться страница логина")
                .isTrue();
        loginPage.setLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        assertThat(homePage.isVisibleCreateOrderButton())
                .as("На главной странице должна появиться кнопка Оформить заказ")
                .isTrue();
    }

    @After
    public void cleanup() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }
}
