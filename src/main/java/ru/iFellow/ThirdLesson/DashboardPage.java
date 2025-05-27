package ru.iFellow.ThirdLesson;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.UtilsWait;

import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class DashboardPage {
    protected final SelenideElement projectList = $x("//a[@href='/browse/TEST']").as("Проекты");
    protected final SelenideElement projectsTest = $x("//div[@id='project_current']//li[@id='admin_main_proj_link']").as("Раздел 'Тесты'");

    @Step("Переход в проекты -> Test (TEST)")
    public void projectNavigate() {
        projectCheck();
        step("Нажатие кнопки 'Проекты'", () -> projectList.click());
        step("Нажатие кнопки 'Test (TEST)'", () -> projectsTest.click());
    }

    @Step("Ожидание загрузки страницы")
    public void projectCheck() {
        UtilsWait.waitFor(visibilityOf(projectList));
    }
}