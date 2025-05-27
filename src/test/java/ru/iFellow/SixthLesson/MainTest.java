package ru.iFellow.SixthLesson;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.UtilsConfig;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тестирование JIRA-проекта")
@Feature("Управление задачами")
@Owner("Steam")
@Severity(SeverityLevel.CRITICAL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    @Description("Тест проверяет успешный вход в систему и переход на главную страницу")
    void testStepOne() {
        step("Ожидание элементов формы входа", loginPage::loginWait);
        step("Выполнение входа", loginPage::login);
        step("Проверка успешного входа", () -> {
            dashboardPage.projectCheck();
            step("Проверка адресной строки", () -> {
                String checkUrl = WebDriverRunner.url();
                assert checkUrl != null;
                assertTrue(checkUrl.contains("Dashboard"));
            });
        });
    }


    @Test
    @DisplayName("Проверка перехода в проекты")
    @Story("Навигация по проектам")
    @Description("Тест проверяет переход в раздел проектов")
    void testStepTwo() {
        testStepOne();
        step("Переход в проекты", () -> {
            dashboardPage.projectNavigate();
            step("Проверка адресной строки", () -> {
                String checkUrl = WebDriverRunner.url();
                assert checkUrl != null;
                assertTrue(checkUrl.contains("projects/TEST"));
            });
        });
    }

    @Test
    @DisplayName("Проверка создания задачи")
    @Story("Управление задачами")
    @Description("Тест проверяет создание новой задачи и счетчик задач")
    void testStepThree() {
        testStepTwo();
        step("Подготовка к созданию задачи", () -> {
            issuesPage.issuesAssert();
            issuesPage.issuesShowAll();
            issuesPage.issueLoader();
            initialCount = issuesPage.issuesCount();
        });
        step("Создание задачи", () -> {
            issuesPage.issueNew();
            createIssuePage.issueCheck();
            createIssuePage.issueBasic("TEST-HW6");
            issueDetailsPage.loaderIssueWait();
            step("Обновление страницы для обновления счетчика", Selenide::refresh);
            issuesPage.issueLoader();
            newCount = issuesPage.issuesCount();
            step("Проверка того, что количество задач выросло на 1", () -> assertEquals(newCount, (initialCount + 1)));
        });
    }


    @Test
    @DisplayName("Проверка версии и статуса задачи")
    @Story("Детали задачи")
    @Description("Тест проверяет версию и статус существующей задачи")
    void testStepFour() {
        testStepThree();
        step("Поиск и проверка задачи", () -> {
            issuesPage.issuesAllNavigate();
            issueDetailsPage.assertSearchElements();
            issueDetailsPage.searchForIssue(UtilsConfig.getProperty("caseName"));
            issueDetailsPage.issueVerify("СДЕЛАТЬ", UtilsConfig.getProperty("version"));
        });
    }

    @Test
    @DisplayName("Проверка изменения статуса задачи")
    @Story("Изменение статуса")
    @Description("Тест проверяет процесс изменения статуса задачи")
    void testStepFive() {
        testStepFour();
        step("Создание и проверка задачи", () -> {
            issuesPage.issueNew();
            createIssuePage.issueDetailed("HW6" + newCount, "Im a chill little entry");
            issueDetailsPage.loaderIssueWait();
            issueDetailsPage.quickSearch("HW6" + newCount);
            issueDetailsPage.issueStatus();
            issueDetailsPage.issueUpdate();
            issueDetailsPage.issueVerify("ГОТОВО", null);
        });
    }
}