package ru.iFellow.ThirdLesson;

import com.codeborne.selenide.SelenideElement;
import utils.UtilsWait;

import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class DashboardPage {
    protected final SelenideElement projectList = $x("//a[@href='/browse/TEST']").as("Проекты");
    protected final SelenideElement projectsTest = $x("//div[@id='project_current']//li[@id='admin_main_proj_link']").as("Раздел 'Тесты'");

    public void projectNavigate() {
        projectCheck();
        projectList.click();
        projectsTest.click();
    }

    public void projectCheck() {
        UtilsWait.waitFor(visibilityOf(projectList));
    }
}