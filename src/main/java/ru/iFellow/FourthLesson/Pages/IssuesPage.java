package ru.iFellow.FourthLesson.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$x;

public class IssuesPage {
    protected final SelenideElement taskFilter = $x("//button[contains(@class, 'subnavigator-trigger')]").as("Переключить фильтр");
    protected final SelenideElement taskFilterAll = $x("//a[@data-item-id='allissues']").as("Все задачи, фильтр");
    protected final SelenideElement taskCounter = $x("//div[@class='showing']/child::span").as("Счетчик задач");
    protected final SelenideElement taskNew = $x("//a[@id='create_link']").as("Генератор задач");
    protected final SelenideElement issuesAll = $x("//div[@id='full-issue-navigator']/child::a").as("Все задачи, навигатор");
    protected final SelenideElement issuesSpinner = $x("//div[@data-testid='centered-spinner']").as("Подгрузка фильтра");

    public int issuesCount() {
        String text = taskCounter.getText();
        return Integer.parseInt(text.split(" из ")[1]);
    }

    public void issuesShowAll() {
        taskFilter.shouldBe(interactable);
        taskFilter.click();
        taskFilterAll.shouldBe(interactable);
        taskFilterAll.click();
    }

    public void issueNew() {
        taskNew.shouldBe(interactable);
        taskNew.click();
    }

    public void issueLoader() {
        issuesSpinner.should(exist);
        issuesSpinner.shouldNot(exist);
    }

    public void issuesAllNavigate() {
        taskNew.shouldBe(interactable);
        issuesAll.click();
    }

    public void issuesAssert() {
        taskFilter.shouldBe(interactable);
        taskNew.should(interactable);
    }
}
