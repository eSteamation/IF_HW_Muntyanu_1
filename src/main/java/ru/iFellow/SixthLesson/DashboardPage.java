package ru.iFellow.SixthLesson;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardPage {
    protected final SelenideElement projectList = $x("//a[@href='/browse/TEST']").as("Проекты");
    protected final SelenideElement projectsTest = $x("//div[@id='project_current']//li[@id='admin_main_proj_link']").as("Раздел 'Тесты'");

    @Step("Переход в проекты -> Test (TEST)")
    public void projectNavigate() {
        projectCheck();
        projectList.click();
        projectsTest.click();
    }

    @Step("Ожидание загрузки страницы")
    public void projectCheck() {
        projectList.shouldBe(visible);
    }

    @Step("Проверка местонахождения по ключевому слову '{keyword}'")
    public void pageVerification(String keyword) {
        String checkUrl = WebDriverRunner.url();
        assert checkUrl != null;
        assertTrue(checkUrl.contains(keyword));
    }
}