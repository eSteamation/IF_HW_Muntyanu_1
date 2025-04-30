package ru.iFellow.ThirdLesson;

import com.codeborne.selenide.SelenideElement;
import utils.UtilsWait;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class IssueDetailsPage {
    protected final SelenideElement searcherQuery = $x("//input[@id='searcher-query']").as("Поисковая строка");
    protected final SelenideElement searchCommit = $x("//button[@class='aui-button aui-button-primary search-button']").as("Поиск");
    protected final SelenideElement statusCheck = $x("//span[@id='status-val']/child::span").as("Проверка статуса");
    protected final SelenideElement versionCheck = $x("//span[@id='fixVersions-field']/a").as("Проверка версии");
    protected final SelenideElement quickSearchInput = $x("//input[@id='quickSearchInput']").as("Быстрый поиск");
    protected final SelenideElement issueInWork = $x("//a[@id='action_id_21']").as("В работе");
    protected final SelenideElement issueTransition = $x("//a[@id='opsbar-transitions_more']").as("Бизнес-процесс");
    protected final SelenideElement issueFinished = $x("//aui-item-link[@id='action_id_51']/a").as("Исполнено");
    protected final SelenideElement issueFinishedSubmit = $x("//input[@name='Transition']").as("Исполнено подтвердить");
    protected final SelenideElement issueCompleted = $x("//aui-item-link[@id='action_id_31']").as("Выполнено");
    protected final SelenideElement loader = $x("//div[@class='jira-spinner-container']").as("Загрузка страницы");
    protected final SelenideElement loaderCreateIssue = $x("//div[@class='aui-spinner spinner']").as("Выход из генератора");
    protected final SelenideElement updateSuccess = $x("//div[contains(@class, 'aui-message-success') and contains(text(), 'был обновлен')]");

    public void searchForIssue(String query) {
        UtilsWait.waitFor(visibilityOf(searcherQuery));
        searcherQuery.setValue(query);
        searchCommit.click();
    }

    public void quickSearch(String query) {
        UtilsWait.waitFor(visibilityOf(quickSearchInput));
        quickSearchInput.setValue(query).pressEnter();
    }

    public void issueVerify(String expectedStatus, String expectedVersion) {
        assertEquals(expectedStatus, statusCheck.getText());
        if (expectedVersion != null) {
            assertEquals(expectedVersion, versionCheck.getText());
        }
    }

    public void loaderIssueWait() {
        UtilsWait.waitFor(visibilityOf(loaderCreateIssue));
        UtilsWait.waitFor(invisibilityOf(loaderCreateIssue));
    }

    public void loaderWait() {
        UtilsWait.waitFor(visibilityOf(loader));
        UtilsWait.waitFor(invisibilityOf(loader));
    }

    public void issueStatus() {
        UtilsWait.waitFor(visibilityOf(issueInWork));
        issueInWork.click();
        loaderWait();
        issueUpdate();
        UtilsWait.waitFor(visibilityOf(issueTransition));
        issueTransition.click();
        UtilsWait.waitFor(visibilityOf(issueFinished));
        issueFinished.click();
        UtilsWait.waitFor(visibilityOf(issueFinishedSubmit));
        issueFinishedSubmit.click();
        loaderIssueWait();
        UtilsWait.waitFor(visibilityOf(issueTransition));
        issueTransition.click();
        UtilsWait.waitFor(visibilityOf(issueCompleted));
        issueCompleted.click();
    }

    public void issueUpdate() {
        UtilsWait.waitFor(visibilityOf(updateSuccess));
        UtilsWait.waitFor(invisibilityOf(updateSuccess));
    }

    public void assertSearchElements() {
        UtilsWait.waitFor(visibilityOf(searcherQuery));
        UtilsWait.waitFor(visibilityOf(searchCommit));
    }
}