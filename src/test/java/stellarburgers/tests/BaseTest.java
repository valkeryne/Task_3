package stellarburgers.tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import org.openqa.selenium.chrome.ChromeOptions;

import org.junit.After;
import org.junit.Before;

import stellarburgers.api.UserApi;
import stellarburgers.models.User;

import java.util.UUID;

public class BaseTest {

    protected final UserApi userApi = new UserApi();

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        Configuration.baseUrl = "https://qa-stellarburgers.education-services.ru";
        Configuration.timeout = 8000;
        Configuration.headless = false;
        Configuration.browser = "chrome";
        ChromeOptions options = new ChromeOptions();
        if (browser.equalsIgnoreCase("yandex")) {
            String yandexBrowserBinaryPath = System.getProperty("yandexBrowserBinaryPath");
            if (yandexBrowserBinaryPath != null && !yandexBrowserBinaryPath.isBlank()) {
                options.setBinary(yandexBrowserBinaryPath);
                Configuration.browserCapabilities = options;
            }
        }
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    protected User generateUser() {
        String uid = UUID.randomUUID().toString().substring(0, 5);
        return new User(
                "UI_User_" + uid,
                "ui_user_" + uid + "@yandex.ru",
                "Pass_" + uid
        );
    }
}
