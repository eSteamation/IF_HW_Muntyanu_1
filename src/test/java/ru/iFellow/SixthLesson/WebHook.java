package ru.iFellow.SixthLesson;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.iFellow.SixthLesson.utilites.AllureReport;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHook {
    @BeforeAll
    public static void setupAllure() {
        AllureSelenide allureSelenide = new AllureSelenide()
                .screenshots(true)
                .savePageSource(true);
        SelenideLogger.addListener("AllureSelenide", allureSelenide);
    }

    @BeforeEach
    public void setup() {
        Configuration.baseUrl = "https://edujira.ifellow.ru/login.jsp";
        open("");
        getWebDriver().manage().window().maximize();
        Configuration.timeout = 10000;
    }

    @AfterAll
    static void generateAllureReport() {
        AllureReport.generateReport();
        AllureReport.openAllureReport();
        SelenideLogger.removeAllListeners();
    }

    @AfterEach
    public void closure(){
        Selenide.closeWebDriver();
    }
}

