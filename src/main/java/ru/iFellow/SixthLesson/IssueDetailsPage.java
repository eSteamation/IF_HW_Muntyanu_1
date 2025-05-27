package ru.iFellow.SixthLesson;

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

    public void searchForIssue(String query) {
        step("Поиск должен быть доступен", () -> searcherQuery.shouldBe(interactable));
        step("Ищем задачу " + query, () -> {
            searcherQuery.setValue(query);
            searchCommit.click();
        });
    }

    public void quickSearch(String query) {
        step("Быстрый поиск должен быть доступен", () -> quickSearchInput.shouldBe(interactable));
        step("Ищем задачу " + query, () -> quickSearchInput.setValue(query).pressEnter());
    }

    public void issueVerify(String expectedStatus, String expectedVersion) {
        step("Проверяем статус задачи " + expectedStatus, () -> assertEquals(expectedStatus, statusCheck.getText()));
        if (expectedVersion != null) {
            step("Проверяем версию задачи " + expectedVersion, () -> assertEquals(expectedVersion, versionCheck.getText()));
        }
    }

    public void loaderIssueWait() {
        step("Ожидание загрузки задач", () -> {
            loaderCreateIssue.shouldBe(exist);
            loaderCreateIssue.shouldNotBe(exist);
        });
    }


    public void loaderWait() {
        step("Ожидание подгрузки элементов", () -> {
            loader.should(exist);
            loader.shouldNot(exist);
        });
    }

    @Step("Проверка переходов по статусам")
    public void issueStatus() {
        step("Перевод в статус 'В работе'", () -> {
            issueInWork.shouldBe(interactable);
            issueInWork.click();
        });
        loaderWait();
        issueUpdate();
        step("Перевод в статус 'В процессе'", () -> {
            issueTransition.shouldBe(interactable);
            issueTransition.click();
            issueFinished.shouldBe(interactable);
            issueFinished.click();
            issueFinishedSubmit.shouldBe(interactable);
            issueFinishedSubmit.click();
        });
        issueUpdate();
        step("Перевод в статус 'Выполнено'", () -> {
            issueTransition.shouldBe(interactable);
            issueTransition.click();
            issueCompleted.shouldBe(interactable);
            issueCompleted.click();
        });
    }

    public void issueUpdate() {
        step("Проверка успешности обновления", () -> {
            updateSuccess.should(appear);
            updateSuccess.should(disappear);
        });
    }

    public void assertSearchElements() {
        step("Проверка доступности поисковой строки", () -> {
            searcherQuery.shouldBe(visible);
            searchCommit.shouldBe(interactable);
        });
    }
}