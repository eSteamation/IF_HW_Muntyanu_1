package ru.iFellow.ThirdLesson;

import com.codeborne.selenide.SelenideElement;
import utils.UtilsWait;

import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class IssuesPage {
    protected final SelenideElement taskFilter = $x("//button[contains(@class, 'subnavigator-trigger')]").as("Переключить фильтр");
    protected final SelenideElement taskFilterAll = $x("//a[@data-item-id='allissues']").as("Все задачи");
    protected final SelenideElement taskCounter = $x("//div[@class='showing']/child::span").as("Счетчик задач");
    protected final SelenideElement taskNew = $x("//a[@id='create_link']").as("Генератор задач");
    protected final SelenideElement issuesAll = $x("//div[@id='full-issue-navigator']/child::a").as("Все задачи");


    public int issuesCount() {
        String text = taskCounter.getText();
        return Integer.parseInt(text.split(" из ")[1]);
    }

    public void issuesShowAll() {
        UtilsWait.waitFor(visibilityOf(taskFilter));
        taskFilter.click();
        UtilsWait.waitFor(visibilityOf(taskFilterAll));
        taskFilterAll.click();
    }

    public void issueNew() {
        UtilsWait.waitFor(visibilityOf(taskNew));
        taskNew.click();
    }

    public void issuesAllNavigate() {
        UtilsWait.waitFor(visibilityOf(taskNew));
        issuesAll.click();
    }

    public void issuesAssert() {
        UtilsWait.waitFor(visibilityOf(taskFilter));
        UtilsWait.waitFor(visibilityOf(taskNew));
    }
}
