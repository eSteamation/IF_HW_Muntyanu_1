package ru.iFellow.ThirdLesson;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.UtilsWait;

import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class IssuesPage {
    protected final SelenideElement taskFilter = $x("//button[contains(@class, 'subnavigator-trigger')]").as("Переключить фильтр");
    protected final SelenideElement taskFilterAll = $x("//a[@data-item-id='allissues']").as("Все задачи, фильтр");
    protected final SelenideElement taskCounter = $x("//div[@class='showing']/child::span").as("Счетчик задач");
    protected final SelenideElement taskNew = $x("//a[@id='create_link']").as("Генератор задач");
    protected final SelenideElement issuesAll = $x("//div[@id='full-issue-navigator']/child::a").as("Все задачи, навигатор");

    @Step("Взятие значения счетчика задач")
    public int issuesCount() {
        String text = taskCounter.getText();
        return Integer.parseInt(text.split(" из ")[1]);
    }

    @Step("Переключение фильтра в режим 'Все задачи'")
    public void issuesShowAll() {
        step("Проверка видимости переключателя фильтра", () -> UtilsWait.waitFor(visibilityOf(taskFilter)));
        step("Нажатие кнопки 'Переключить фильтр'", () -> taskFilter.click());
        step("Проверка видимости выбора 'Все задачи'", () -> UtilsWait.waitFor(visibilityOf(taskFilterAll)));
        step("Подтверждение выбора 'Все задачи'", () -> taskFilterAll.click());
    }

    @Step("Создание новой задачи")
    public void issueNew() {
        step("Проверка видимости генератора задач", () -> UtilsWait.waitFor(visibilityOf(taskNew)));
        step("Переход в создание задачи", () -> taskNew.click());
    }

    @Step("Посмотреть все задачи и фильтры")
    public void issuesAllNavigate() {
        UtilsWait.waitFor(visibilityOf(taskNew));
        issuesAll.click();
    }

    @Step("Проверка")
    public void issuesAssert() {
        UtilsWait.waitFor(visibilityOf(taskFilter));
        UtilsWait.waitFor(visibilityOf(taskNew));
    }
}
