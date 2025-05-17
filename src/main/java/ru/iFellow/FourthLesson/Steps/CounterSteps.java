package ru.iFellow.FourthLesson.Steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.iFellow.FourthLesson.Pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CounterSteps {
    protected final LoginPage loginPage = new LoginPage();
    protected final DashboardPage dashboardPage = new DashboardPage();
    protected final IssuesPage issuesPage = new IssuesPage();
    protected final CreateIssuePage createIssuePage = new CreateIssuePage();
    protected final IssueDetailsPage issueDetailsPage = new IssueDetailsPage();
    private int initialCount;


    @Дано("в аккаунте, на панели 'Открытые задачи' проекта 'Test TEST'")
    public void testPartTwo() {
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
    }

    @Когда("меняем фильтр на 'Все задачи'")
    public void issuesAll() {
        issuesPage.issuesAssert();
        issuesPage.issuesShowAll();
    }

    @И("проверяем общее число задач")
    public void issuesCounter() {
        issuesPage.issueLoader();
        initialCount = issuesPage.issuesCount();
    }

    @Тогда("создаем новую задачу {string}")
    public void issuesNew(String issueName) {
        issuesPage.issueNew();
        createIssuePage.issueCheck();
        createIssuePage.issueBasic(issueName);
        issueDetailsPage.loaderIssueWait();
    }

    @И("проверяем изменение счетчика")
    public void counterVerify() {
        Selenide.refresh();
        int newCount = issuesPage.issuesCount();
        assertEquals(newCount, (initialCount + 1));
    }
}
