package ru.iFellow.ThirdLesson;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import utils.UtilsWait;

import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class CreateIssuePage {
    protected final SelenideElement topicField = $x("//input[@id='summary']").as("Тема задачи");
    protected final SelenideElement topicCommit = $x("//input[@id='create-issue-submit']").as("Создать задачу");
    protected final SelenideElement modeSelect = $x("//li[@data-mode='wysiwyg']/button").as("Выбор формата");
    protected final SelenideElement descriptionField = $x("//iframe[@id='mce_0_ifr']").as("Описание");
    protected final SelenideElement descriptionInput = $x("//body[@id='tinymce']").as("Ввод описания");
    protected final SelenideElement versionSelect = $x("//select[@id='fixVersions']").as("Выбор версии");
    protected final SelenideElement tagField = $x("//div[@id='labels-multi-select']").as("Поле для тегов");
    protected final SelenideElement tagSuggest = $x("//div[@id='labels-multi-select']/span").as("Выпадающее окно тегов");
    protected final SelenideElement tagSelectBugFix = $x("//li[starts-with(@id, 'bugfix-')]/a").as("Кнопка тега");
    protected final SelenideElement issueSuggest = $x("//div[@id='issuelinks-issues-multi-select']/span").as("Выпадающее окно задач");
    protected final SelenideElement issueSelect = $x("//li[starts-with(@id, 'test-121544')]/a").as("Выбор задачи");
    protected final SelenideElement epicSuggest = $x("//div[@id='customfield_10100-single-select']/span").as("Выпадающее окно эпиков");
    protected final SelenideElement epicSelect = $x("//li[starts-with(@id, '06/jul/19-3:25')]/descendant::span[text()='TEST-174476)']").as("Выбор эпика");
    protected final SelenideElement sprintSuggest = $x("//div[@id='js-customfield_10104-ss-container']").as("Выпадающее окно спринта");
    protected final SelenideElement sprintSelect = $x("//li[starts-with(@id, 'доска-спринт-1')]/a").as("Выбор спринта");

    public void issueBasic(String summary) {
        issueCheck();
        topicField.setValue(summary);
        topicCommit.click();
    }

    public void issueCheck() {
        UtilsWait.waitFor(visibilityOf(modeSelect));
        UtilsWait.waitFor(visibilityOf(topicField));
        UtilsWait.waitFor(visibilityOf(versionSelect));
        UtilsWait.waitFor(visibilityOf(topicCommit));
    }


    public void issueDetailed(String summary, String description) {
        issueCheck();
        modeSelect.click();
        topicField.setValue(summary);
        UtilsWait.waitFor(visibilityOf(descriptionField));
        descriptionField.click();
        Selenide.switchTo().frame(descriptionField);
        descriptionInput.setValue(description);
        Selenide.switchTo().defaultContent();
        versionSelect.selectOptionByValue("10001");
        tagField.scrollIntoView(true);
        UtilsWait.waitFor(visibilityOf(tagSuggest));
        tagSuggest.click();
        UtilsWait.waitFor(visibilityOf(tagSelectBugFix));
        tagSelectBugFix.click();
        UtilsWait.waitFor(visibilityOf(issueSuggest));
        issueSuggest.click();
        UtilsWait.waitFor(visibilityOf(issueSelect));
        issueSelect.click();
        UtilsWait.waitFor(visibilityOf(epicSuggest));
        epicSuggest.click();
        UtilsWait.waitFor(visibilityOf(epicSelect));
        epicSelect.click();
        UtilsWait.waitFor(visibilityOf(sprintSuggest));
        sprintSuggest.click();
        UtilsWait.waitFor(visibilityOf(sprintSelect));
        sprintSelect.click();
        topicCommit.click();
    }

}