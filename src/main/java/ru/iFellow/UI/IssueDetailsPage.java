package ru.iFellow.UI;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Step("Поиск задачи {query}")
    public void searchForIssue(String query) {
        searcherQuery.shouldBe(interactable);
        searcherQuery.setValue(query);
        searchCommit.click();
    }

    @Step("Быстрый поиск задачи {query}")
    public void quickSearch(String query) {
        quickSearchInput.shouldBe(interactable);
        quickSearchInput.setValue(query).pressEnter();
    }

    @Step("Проверка статуса задачи '{expectedStatus}'")
    public void issueVerify(String expectedStatus, String expectedVersion) {
        assertEquals(expectedStatus, statusCheck.getText());
        if (expectedVersion != null) {
            step("Проверка версии задачи '{expectedVersion}'", () -> assertEquals(expectedVersion, versionCheck.getText()));
        }
    }

    @Step("Ожидание прогрузки задач")
    public void loaderIssueWait() {
        loaderCreateIssue.shouldBe(exist);
        loaderCreateIssue.shouldNotBe(exist);
    }

    @Step("Ожидание прогрузки страницы")
    public void loaderWait() {
        loader.should(exist);
        loader.shouldNot(exist);
    }

    @Step("Проверка переходов по статусам")
    public void issueStatus() {
        statusInWork();
        loaderWait();
        issueUpdate();
        statusInProgress();
        issueUpdate();
        statusFinished();
    }

    @Step("Проверка успешности обновления")
    public void issueUpdate() {
        updateSuccess.should(appear);
        updateSuccess.should(disappear);
    }

    @Step("Проверка доступности поисковой строки")
    public void assertSearchElements() {
        searcherQuery.shouldBe(visible);
        searchCommit.shouldBe(interactable);
    }

    @Step("Перевод в статус 'В работе'")
    public void statusInWork() {
        issueInWork.shouldBe(interactable);
        issueInWork.click();
    }

    @Step("Перевод в статус 'В процессе'")
    public void statusInProgress() {
        issueTransition.shouldBe(interactable);
        issueTransition.click();
        issueFinished.shouldBe(interactable);
        issueFinished.click();
        issueFinishedSubmit.shouldBe(interactable);
        issueFinishedSubmit.click();
    }

    @Step("Перевод в статус 'Выполнено'")
    public void statusFinished() {
        issueTransition.shouldBe(interactable);
        issueTransition.click();
        issueCompleted.shouldBe(interactable);
        issueCompleted.click();
    }
}