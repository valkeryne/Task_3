package stellarburgers.tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.models.User;
import stellarburgers.pageobjects.LoginPage;
import stellarburgers.pageobjects.RegisterPage;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterTest extends BaseTest {

    private RegisterPage registerPage;
    private User user;
    private String accessToken;

    @Before
    public void openRegisterPage() {
        registerPage = Selenide.open("/register", RegisterPage.class);
        user = generateUser();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка, что пользователь успешно зарегистрировался. Открылась страница логина")
    public void registerUser() {
        registerPage.setRegisterForm(
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
        registerPage.clickRegisterButton();

        LoginPage loginPage = Selenide.page(LoginPage.class);
        assertThat(loginPage.isDisplayedLoginButton())
                .as("Страница логина отображается")
                .isTrue();

        Response loginResponse = userApi.login(user.getCredentials());
        accessToken = loginResponse.path("accessToken");
    }

    @Test
    @DisplayName("Ошибка регистрации пользователя")
    @Description("Пароль не подходит по длине")
    public void registerUserWithBadPassword() {
        registerPage.setRegisterForm(
                user.getName(),
                user.getEmail(),
                "123"
        );
        registerPage.clickRegisterButton();
        assertThat(registerPage.isVisiblePasswordErrorText())
                .as("Появилась ошибка неправильного пароля")
                .isTrue();
    }

    @After
    public void cleanup() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }
}
