package ru.iFellow.ThridLesson;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.iFellow.ThirdLesson.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest extends WebHook {
    protected final LoginPage loginPage = new LoginPage();
    protected final DashboardPage dashboardPage = new DashboardPage();
    protected final IssuesPage issuesPage = new IssuesPage();
    protected final CreateIssuePage createIssuePage = new CreateIssuePage();
    protected final IssueDetailsPage issueDetailsPage = new IssueDetailsPage();

    @Test
    @DisplayName("Проверка входа")
    public void testStepOne() {
        loginPage.loginWait();
        loginPage.login();
        dashboardPage.projectCheck();
        String checkUrl = WebDriverRunner.url();
        assert checkUrl != null;
        assertTrue(checkUrl.contains("Dashboard"));
    }

    @Test
    @DisplayName("Проверка перехода в проекты")
    public void testStepTwo() {
        testStepOne();
        dashboardPage.projectNavigate();
        String checkUrl = WebDriverRunner.url();
        assert checkUrl != null;
        assertTrue(checkUrl.contains("projects/TEST"));
    }

    @Test
    @DisplayName("Проверка счетчика")
    public void testStepThree() {
        testStepTwo();
        issuesPage.issuesAssert();
        issuesPage.issuesShowAll();
        issuesPage.issueNew();
        createIssuePage.issueCheck();
        createIssuePage.issueBasic("TEST-HW3");
        int initialCount = issuesPage.issuesCount();
        issueDetailsPage.loaderIssueWait();
        Selenide.refresh();
        int newCount = issuesPage.issuesCount();
        assertEquals(newCount, (initialCount + 1));
    }

    @Test
    @DisplayName("Проверка версии и статуса")
    public void testStepFour() {
        testStepThree();
        issuesPage.issuesAllNavigate();
        issueDetailsPage.assertSearchElements();
        issueDetailsPage.searchForIssue("TestSeleniumATHomework");
        issueDetailsPage.issueVerify("СДЕЛАТЬ", "Version 2.0");
    }

    @Test
    @DisplayName("Проверка перехода по статусам")
    public void testStepFive() {
        testStepFour();
        int newCount = issuesPage.issuesCount();
        issuesPage.issueNew();
        createIssuePage.issueDetailed("HW" + (newCount), "Im a chill little entry");
        issueDetailsPage.loaderIssueWait();
        issueDetailsPage.quickSearch("HW" + (newCount));
        issueDetailsPage.issueStatus();
        issueDetailsPage.issueUpdate();
        issueDetailsPage.issueVerify("ГОТОВО", null);
    }
}