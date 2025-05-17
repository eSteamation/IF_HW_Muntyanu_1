package ru.iFellow.FourthLesson.Steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.iFellow.FourthLesson.Pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerificationSteps {
    protected final LoginPage loginPage = new LoginPage();
    protected final DashboardPage dashboardPage = new DashboardPage();
    protected final IssuesPage issuesPage = new IssuesPage();
    protected final CreateIssuePage createIssuePage = new CreateIssuePage();
    protected final IssueDetailsPage issueDetailsPage = new IssueDetailsPage();

    @Дано("в аккаунте, в окне задачи, после создания задачи {string} и проверки счетчика.")
    public void testStepThree(String issueName) {
        loginPage.loginWait();
        loginPage.loginCredentials();
        loginPage.loginClick();
        dashboardPage.projectCheck();
        String checkUrl = WebDriverRunner.url();
        assert checkUrl != null;
        assertTrue(checkUrl.contains("Dashboard"));
        dashboardPage.projectNavigate();
        checkUrl = WebDriverRunner.url();
        assert checkUrl != null;
        assertTrue(checkUrl.contains("projects/TEST"));
        issuesPage.issuesAssert();
        issuesPage.issuesShowAll();
        issuesPage.issueLoader();
        int initialCount = issuesPage.issuesCount();
        issuesPage.issueNew();
        createIssuePage.issueCheck();
        createIssuePage.issueBasic(issueName);
        issueDetailsPage.loaderIssueWait();
        Selenide.refresh();
        int newCount = issuesPage.issuesCount();
        assertEquals(newCount, (initialCount + 1));
    }

    @Когда("находим задачу {string}")
    public void issueSearch(String searchInput) {
        issuesPage.issuesAllNavigate();
        issueDetailsPage.assertSearchElements();
        issueDetailsPage.searchForIssue(searchInput);
    }

    @Тогда("проверяем статус {string} и версию {string} задачи")
    public void issueVerification(String status, String version) {
        issueDetailsPage.issueVerify(status, version);
    }
}
