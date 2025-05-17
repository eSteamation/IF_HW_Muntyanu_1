package ru.iFellow.FourthLesson.Steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.iFellow.FourthLesson.Pages.DashboardPage;
import ru.iFellow.FourthLesson.Pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardSteps {
    protected final LoginPage loginPage = new LoginPage();
    protected final DashboardPage dashboardPage = new DashboardPage();

    @Дано("^на панели управления, после входа в систему")
    public void login() {
        loginPage.loginWait();
        loginPage.loginCredentials();
        loginPage.loginClick();
        dashboardPage.projectCheck();
        String checkUrl = WebDriverRunner.url();
        assert checkUrl != null;
        assertTrue(checkUrl.contains("Dashboard"));
    }

    @Когда("открыли проект 'Test (TEST)'")
    public void projectSwitch() {
        dashboardPage.projectNavigate();
    }

    @Тогда("проверяем, что переход был успешен")
    public void projectVerify() {
        String checkUrl = WebDriverRunner.url();
        assert checkUrl != null;
        assertTrue(checkUrl.contains("projects/TEST"));
    }
}
