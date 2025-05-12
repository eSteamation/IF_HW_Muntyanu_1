package ru.iFellow.FourthLesson.Hooks;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHook {

    @Before
    public void setup() {
        Configuration.baseUrl = "https://edujira.ifellow.ru";
        Configuration.timeout = 10000;
        open("");
        getWebDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}
