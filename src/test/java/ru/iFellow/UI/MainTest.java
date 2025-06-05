package ru.iFellow.UI;

import Utilites.UtilsConfig;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.iFellow.UI.Hooks.WebHook;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тестирование JIRA-проекта")
@Feature("Управление задачами")
@Owner("Steam")
@Severity(SeverityLevel.CRITICAL)
public class MainTest extends WebHook {
    protected final LoginPage loginPage = new LoginPage();
    protected final DashboardPage dashboardPage = new DashboardPage();
    protected final IssuesPage issuesPage = new IssuesPage();
    protected final CreateIssuePage createIssuePage = new CreateIssuePage();
    protected final IssueDetailsPage issueDetailsPage = new IssueDetailsPage();
    protected int initialCount;
    protected int newCount;

    @Test
    @DisplayName("Проверка входа в систему")
    @Story("Авторизация пользователя")
    @Description("Вход в систему и переход на главную страницу")
    void testStepOne() {
        loginPage.loginWait();
        loginPage.login(UtilsConfig.getProperty("username"), UtilsConfig.getProperty("password"));
        dashboardPage.projectCheck();
        dashboardPage.pageVerification("Dashboard");
    }


    @Test
    @DisplayName("Проверка перехода в проекты")
    @Story("Навигация по проектам")
    @Description("Переход в раздел проектов {keyword}")
    void testStepTwo() {
        testStepOne();
        dashboardPage.projectNavigate();
        dashboardPage.pageVerification("projects/TEST");
    }

    @Test
    @DisplayName("Проверка создания задачи")
    @Story("Управление задачами")
    @Description("Cоздание новой задачи и счетчик задач")
    void testStepThree() {
        testStepTwo();
        issuesPage.issuesAssert();
        issuesPage.issuesShowAll();
        issuesPage.issueLoader();
        issuesPage.issueNew();
        createIssuePage.issueCheck();
        createIssuePage.issueBasic("TEST-HW6");
        issueDetailsPage.loaderIssueWait();
        initialCount = issuesPage.issuesCount();
        step("Обновление страницы для обновления счетчика", Selenide::refresh);
        issuesPage.issueLoader();
        newCount = issuesPage.issuesCount();
        step("Проверка того, что количество задач выросло на 1", () -> assertEquals(newCount, (initialCount + 1)));
    }


    @Test
    @DisplayName("Проверка версии и статуса задачи")
    @Story("Детали задачи")
    @Description("Тест проверяет версию и статус существующей задачи")
    void testStepFour() {
        testStepThree();
        issuesPage.issuesAllNavigate();
        issueDetailsPage.assertSearchElements();
        issueDetailsPage.searchForIssue(UtilsConfig.getProperty("caseName"));
        issueDetailsPage.issueVerify("СДЕЛАТЬ", UtilsConfig.getProperty("version"));
    }

    @Test
    @DisplayName("Проверка изменения статуса задачи")
    @Story("Изменение статуса")
    @Description("Тест проверяет процесс изменения статуса задачи")
    void testStepFive() {
        testStepFour();
        issuesPage.issueNew();
        createIssuePage.issueDetailed("HW6" + newCount, "Im a chill little entry");
        issueDetailsPage.loaderIssueWait();
        issueDetailsPage.quickSearch("HW6" + newCount);
        issueDetailsPage.issueStatus();
        issueDetailsPage.issueUpdate();
        issueDetailsPage.issueVerify("ГОТОВО", null);
    }
}