package ru.iFellow.SixthLesson;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

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

    @Step("Создание базовой задачи с темой '{summary}'")
    public void issueBasic(String summary) {
        step("Проверка видимости обязательных полей", this::issueCheck);
        step("Ввод темы задачи: " + summary, () -> {
            topicField.setValue(summary);
        });
        step("Нажатие кнопки создать", () -> topicCommit.click());
    }

    @Step("Проверка видимости полей")
    public void issueCheck() {
        step("Ожидание элемента выбора формата", () -> modeSelect.shouldBe(visible));
        step("Ожидание поля темы", () -> topicField.shouldBe(visible));
        step("Ожидание выбора версии", () -> versionSelect.shouldBe(visible));
        step("Ожидание кнопки подтверждения", () -> topicCommit.shouldBe(visible));
    }

    @Step("Детализированное создание задачи c темой '{summary}'")
    public void issueDetailed(String summary, String description) {
        issueCheck();
        step("Переход в текстовый формат", () -> modeSelect.click());
        step("Ввод темы задачи: " + summary, () -> topicField.setValue(summary));
        step("Ввод описания задачи", () ->
        {
            descriptionField.shouldBe(interactable);
            descriptionField.click();
            Selenide.switchTo().frame(descriptionField);
            descriptionInput.setValue(description);
            Selenide.switchTo().defaultContent();
        });
        step("Выбор версии", () -> versionSelect.selectOptionByValue("10001"));
        step("Добавление тега Bugfix", () -> {
            tagField.scrollIntoView(true);
            tagSuggest.shouldBe(interactable);
            tagSuggest.click();
            tagSelectBugFix.shouldBe(interactable);
            tagSelectBugFix.click();
        });
        step("Привязка связанной задачи", () -> {
            issueSuggest.shouldBe(interactable);
            issueSuggest.click();
            issueSelect.shouldBe(interactable);
            issueSelect.click();
        });
        step("Выбор эпика", () -> {
            epicSuggest.shouldBe(interactable);
            epicSuggest.click();
            epicSelect.shouldBe(interactable);
            epicSelect.click();
        });
        step("Выбор спринта", () -> {
            sprintSuggest.shouldBe(interactable);
            sprintSuggest.click();
            sprintSelect.shouldBe(interactable);
            sprintSelect.click();
        });
        step("Подтверждение создания", () -> topicCommit.click());
    }
}