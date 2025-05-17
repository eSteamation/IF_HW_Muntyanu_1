package ru.iFellow.FourthLesson.Steps;


import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.iFellow.FourthLesson.Pages.DashboardPage;
import ru.iFellow.FourthLesson.Pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    protected final LoginPage loginPage = new LoginPage();
    protected final DashboardPage dashboardPage = new DashboardPage();

    @Дано("^на главной странице")
    public void mainPage() {
        loginPage.loginWait();
    }

    @Когда("^вводим данные для входа")
    public void inputCredentials() {
        loginPage.loginCredentials();
    }

    @И("^Нажимаем кнопку входа")
    public void loginCommit() {
        loginPage.loginClick();
    }

    @Тогда("Проверяем успешность входа")
    public void loginCheck() {
        dashboardPage.projectCheck();
        String checkUrl = WebDriverRunner.url();
        assert checkUrl != null;
        assertTrue(checkUrl.contains("Dashboard"));
    }

}

