package ru.iFellow.UI;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class IssuesPage {
    protected final SelenideElement taskFilter = $x("//button[contains(@class, 'subnavigator-trigger')]").as("Переключить фильтр");
    protected final SelenideElement taskFilterAll = $x("//a[@data-item-id='allissues']").as("Все задачи, фильтр");
    protected final SelenideElement taskCounter = $x("//div[@class='showing']/child::span").as("Счетчик задач");
    protected final SelenideElement taskNew = $x("//a[@id='create_link']").as("Генератор задач");
    protected final SelenideElement issuesAll = $x("//div[@id='full-issue-navigator']/child::a").as("Все задачи, навигатор");
    protected final SelenideElement issuesSpinner = $x("//div[@class='loading']").as("Подгрузка фильтра");

    @Step("Взятие значения счетчика задач")
    public int issuesCount() {
        String text = taskCounter.getText();
        return Integer.parseInt(text.split(" из ")[1]);
    }

    @Step("Переключение фильтра в режим 'Все задачи'")
    public void issuesShowAll() {
        taskFilter.shouldBe(interactable);
        taskFilter.click();
        taskFilterAll.shouldBe(interactable);
        taskFilterAll.click();
    }

    @Step("Создание новой задачи")
    public void issueNew() {
        taskNew.shouldBe(interactable);
        taskNew.click();
    }

    @Step("Ожидание загрузки задачи")
    public void issueLoader() {
        issuesSpinner.should(exist);
        issuesSpinner.should(disappear);
    }

    @Step("Посмотреть все задачи и фильтры")
    public void issuesAllNavigate() {
        taskNew.shouldBe(interactable);
        issuesAll.click();
    }

    @Step("Проверка загрузки страницы задач")
    public void issuesAssert() {
        taskFilter.shouldBe(interactable);
        taskNew.shouldBe(interactable);
    }
}
