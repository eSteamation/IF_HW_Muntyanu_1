package ru.iFellow.FourthLesson.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    protected final SelenideElement projectList = $x("//a[@href='/browse/TEST']").as("Проекты");
    protected final SelenideElement projectsTest = $x("//div[@id='project_current']//li[@id='admin_main_proj_link']").as("Раздел 'Тесты'");

    public void projectNavigate() {
        projectCheck();
        projectList.click();
        projectsTest.click();
    }

    public void projectCheck() {
        projectList.shouldBe(visible);
    }
}