package ru.iFellow.FourthLesson.Steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.iFellow.FourthLesson.Pages.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditorSteps {
    protected final LoginPage loginPage = new LoginPage();
    protected final DashboardPage dashboardPage = new DashboardPage();
    protected final IssuesPage issuesPage = new IssuesPage();
    protected final CreateIssuePage createIssuePage = new CreateIssuePage();
    protected final IssueDetailsPage issueDetailsPage = new IssueDetailsPage();
    protected int newCount;


    @Дано("в аккаунте, после проверки счетчика, в окне задачи, после проверки статуса и версии:")
    public void testStepFour(DataTable dataTable) {
        Map<String, String> parameters = dataTable.asMap(String.class, String.class);
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
        createIssuePage.issueBasic(parameters.get("NameNew"));
        issueDetailsPage.loaderIssueWait();
        Selenide.refresh();
        newCount = issuesPage.issuesCount();
        assertEquals(newCount, (initialCount + 1));
        issuesPage.issuesAllNavigate();
        issueDetailsPage.assertSearchElements();
        issueDetailsPage.searchForIssue(parameters.get("NameSearch"));
        issueDetailsPage.issueVerify(parameters.get("Status"), parameters.get("Version"));
    }

    @Когда("создаем новую задачу, полностью заполненную какими-то данными")
    public void issueDetailed() {
        issuesPage.issueNew();
        createIssuePage.issueDetailed("HW" + (newCount), "Im a chill little entry");
        issueDetailsPage.loaderIssueWait();

    }

    @Тогда("открываем страницу только что созданной задачи")
    public void searchNew() {
        issueDetailsPage.quickSearch("HW" + (newCount));
    }

    @И("проверяем работу функции изменения статуса задачи")
    public void issueCheck() {
        issueDetailsPage.issueStatus();
        issueDetailsPage.issueUpdate();
        issueDetailsPage.issueVerify("ГОТОВО", null);
    }
}
