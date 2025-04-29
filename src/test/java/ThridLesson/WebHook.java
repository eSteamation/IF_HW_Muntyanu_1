package ThridLesson;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHook {
    @BeforeEach
    public void setup() {
        Configuration.baseUrl = "https://edujira.ifellow.ru/login.jsp";
        open("");
        getWebDriver().manage().window().maximize();
        Configuration.timeout = 10000;
       // Configuration.holdBrowserOpen = true; //для более удобного дебаггинга
    }

    @AfterEach
    public void closure(){
        Selenide.closeWebDriver();
    }
}

