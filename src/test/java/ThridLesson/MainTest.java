package ThridLesson;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest extends WebHook {

    private final SelenideElement usernameField = $x("//input[@name='os_username']").as("Поле никнейма");
    private final SelenideElement passwordField = $x("//input[@type='password']").as("Поле пароля");
    private final SelenideElement loginCommit = $x("//div[@class='buttons']//input[@name='login']").as("Вход");
    private final SelenideElement projectsTest = $x("//a[@href='/browse/TEST']").as("Проекты");
    private final SelenideElement projectsRapid = $x("//div[@id='project_current']//li[@id='admin_main_proj_link']").as("Раздел 'Тесты'");
    private final SelenideElement taskFilter = $x("//button[contains(@class, 'subnavigator-trigger')]").as("Переключить фильтр");
    private final SelenideElement taskFilterAll = $x("//a[@data-item-id='allissues']").as("Все задачи");
    private final SelenideElement taskCounter = $x("//div[@class='showing']/child::span").as("Счетчик задач");
    private final SelenideElement taskNew = $x("//a[@id='create_link']").as("Новая задача");
    private final SelenideElement topicField = $x("//input[@id='summary']").as("Тема задачи");
    private final SelenideElement topicCommit = $x("//input[@id='create-issue-submit']").as("Создать задачу");
    private final SelenideElement issuesAll = $x("//div[@id='full-issue-navigator']/child::a").as("Все задачи");
    private final SelenideElement searcherQuery = $x("//input[@id='searcher-query']").as("Поисковая строка");
    private final SelenideElement searchCommit = $x("//button[@class='aui-button aui-button-primary search-button']").as("Поиск");
    private final SelenideElement statusCheck = $x("//span[@id='status-val']/child::span").as("Проверка статуса");
    private final SelenideElement versionCheck = $x("//span[@id='fixVersions-field']/a").as("Проверка версии");
    private final SelenideElement descriptionField = $x("//iframe[@id='mce_0_ifr']").as("Описание");
    private final SelenideElement versionSelect = $x("//select[@id='fixVersions']").as("Выбор версии");
    private final SelenideElement modeSelect = $x("//li[@data-mode='wysiwyg']/button").as("Выбор формата");
    private final SelenideElement tagField = $x("//div[@id='labels-multi-select']").as("Поле для тегов");
    private final SelenideElement tagSuggest = $x("//div[@id='labels-multi-select']/span").as("Выпадающее окно тегов");
    private final SelenideElement tagSelectBugFix = $x("//li[starts-with(@id, 'bugfix-')]/a").as("Кнопка тега");
    private final SelenideElement descriptionInput = $x("//body[@id='tinymce']").as("Ввод описания");
    private final SelenideElement issueSuggest = $x("//div[@id='issuelinks-issues-multi-select']/span").as("Выпадающее окно задач");
    private final SelenideElement issueSelect = $x("//li[starts-with(@id, 'test-121544')]/a").as("Выбор задачи");
    private final SelenideElement epicSuggest = $x("//div[@id='customfield_10100-single-select']/span").as("Выпадающее окно эпиков");
    private final SelenideElement epicSelect = $x("//li[starts-with(@id, '06/jul/19-3:25')]/descendant::span[text()='TEST-174476)']").as("Выбор эпика");
    private final SelenideElement sprintSuggest = $x("//div[@id='js-customfield_10104-ss-container']").as("Выпадающее окно спринта");
    private final SelenideElement sprintSelect = $x("//li[starts-with(@id, 'доска-спринт-1')]/a").as("Выбор спринта");
    private final SelenideElement quickSearchInput = $x("//input[@id='quickSearchInput']").as("Быстрый поиск");
    private final SelenideElement issueCompleted = $x("//aui-item-link[@id='action_id_31']").as("Выполнено");
    private final SelenideElement issueFinished = $x("//aui-item-link[@id='action_id_51']").as("Исполнено");
    private final SelenideElement issueFinishedSubmit = $x("//input[@name='Transition']").as("Исполнено");
    private final SelenideElement issueTransition = $x("//a[@id='opsbar-transitions_more']/child::span").as("Бизнес-процесс");
    private final SelenideElement issueInWork = $x("//a[@id='action_id_21']").as("В работе");



    public int totalCount(){
        String text = taskCounter.getText();
        return Integer.parseInt(text.split(" из ") [1]);
    }

    @Test
    public void testStepOne(){
        assertTrue(usernameField.isDisplayed());
        usernameField.setValue("AT11");
        assertEquals("AT11", usernameField.getAttribute("value"));
        assertTrue(passwordField.isDisplayed());
        passwordField.setValue("Qwerty123");
        assertEquals("Qwerty123", passwordField.getAttribute("value"));
        assertTrue(loginCommit.isDisplayed());
        loginCommit.click();
    }
    @Test
    public void testStepTwo(){
        assertTrue(usernameField.isDisplayed());
        usernameField.setValue("AT11");
        assertEquals("AT11", usernameField.getAttribute("value"));
        assertTrue(passwordField.isDisplayed());
        passwordField.setValue("Qwerty123");
        assertEquals("Qwerty123", passwordField.getAttribute("value"));
        assertTrue(loginCommit.isDisplayed());
        loginCommit.click();
        assertTrue(projectsTest.isDisplayed());
        projectsTest.click();
        Selenide.sleep(1000); //Поскольку в задании указано использовать assert вместо shouldBe, я использую sleep для поддержки assertTrue проверок которые слишком торопятся
        assertTrue(projectsRapid.isDisplayed());
        projectsRapid.click();
    }
    @Test
    public void testStepThree() {
        assertTrue(usernameField.isDisplayed());
        usernameField.setValue("AT11");
        assertEquals("AT11", usernameField.getAttribute("value"));
        assertTrue(passwordField.isDisplayed());
        passwordField.setValue("Qwerty123");
        assertEquals("Qwerty123", passwordField.getAttribute("value"));
        assertTrue(loginCommit.isDisplayed());
        loginCommit.click();
        assertTrue(projectsTest.isDisplayed());
        projectsTest.click();
        Selenide.sleep(1000);
        assertTrue(projectsRapid.isDisplayed());
        projectsRapid.click();
        assertTrue(taskFilter.isDisplayed());
        taskFilter.click();
        assertTrue(taskFilterAll.isDisplayed());
        taskFilterAll.click();
        Selenide.sleep(1000);
        totalCount();
        System.out.println("Number of cases: " + totalCount());
        assertTrue(taskNew.isDisplayed());
        taskNew.click();
        Selenide.sleep(500);
        assertTrue(topicField.isDisplayed());
        topicField.setValue("TEST-HW3");
        assertEquals("TEST-HW3", topicField.getAttribute("value"));
        assertTrue(topicCommit.isDisplayed());
        topicCommit.click();
        Selenide.sleep(1000);
        int caseNumber = totalCount();
        System.out.println("Number of cases: " + caseNumber);
    }

    @Test
    public void testStepFour() {
        assertTrue(usernameField.isDisplayed());
        usernameField.setValue("AT11");
        assertEquals("AT11", usernameField.getAttribute("value"));
        assertTrue(passwordField.isDisplayed());
        passwordField.setValue("Qwerty123");
        assertEquals("Qwerty123", passwordField.getAttribute("value"));
        assertTrue(loginCommit.isDisplayed());
        loginCommit.click();
        assertTrue(projectsTest.isDisplayed());
        projectsTest.click();
        Selenide.sleep(1000);
        assertTrue(projectsRapid.isDisplayed());
        projectsRapid.click();
        assertTrue(taskFilter.isDisplayed());
        taskFilter.click();
        assertTrue(taskFilterAll.isDisplayed());
        taskFilterAll.click();
        Selenide.sleep(1000);
        totalCount();
        System.out.println("Number of cases: " + totalCount());
        assertTrue(taskNew.isDisplayed());
        taskNew.click();
        Selenide.sleep(500);
        assertTrue(topicField.isDisplayed());
        topicField.setValue("TEST-HW3");
        assertEquals("TEST-HW3", topicField.getAttribute("value"));
        assertTrue(topicCommit.isDisplayed());
        topicCommit.click();
        Selenide.sleep(1000);
        int caseNumber = totalCount();
        System.out.println("Number of cases: " + caseNumber);
        assertTrue(issuesAll.isDisplayed());
        issuesAll.click();
        assertTrue(searcherQuery.isDisplayed());
        searcherQuery.setValue("TestSeleniumATHomework");
        assertEquals("TestSeleniumATHomework", searcherQuery.getAttribute("value"));
        assertTrue(searchCommit.isDisplayed());
        searchCommit.click();
        Selenide.sleep(1000);
        assertTrue(statusCheck.isDisplayed());
        Assertions.assertEquals("СДЕЛАТЬ", statusCheck.getText());
        assertTrue(versionCheck.isDisplayed());
        Assertions.assertEquals("Version 2.0", versionCheck.getText());
    }

    @Test
    public void testStepFive() {
        assertTrue(usernameField.isDisplayed());
        usernameField.setValue("AT11");
        assertEquals("AT11", usernameField.getAttribute("value"));
        assertTrue(passwordField.isDisplayed());
        passwordField.setValue("Qwerty123");
        assertEquals("Qwerty123", passwordField.getAttribute("value"));
        assertTrue(loginCommit.isDisplayed());
        loginCommit.click();
        assertTrue(projectsTest.isDisplayed());
        projectsTest.click();
        Selenide.sleep(1000);
        assertTrue(projectsRapid.isDisplayed());
        projectsRapid.click();
        assertTrue(taskFilter.isDisplayed());
        taskFilter.click();
        assertTrue(taskFilterAll.isDisplayed());
        taskFilterAll.click();
        Selenide.sleep(1000);
        totalCount();
        System.out.println("Number of cases: " + totalCount());
        assertTrue(taskNew.isDisplayed());
        taskNew.click();
        Selenide.sleep(500);
        assertTrue(topicField.isDisplayed());
        topicField.setValue("TEST-HW3");
        assertEquals("TEST-HW3", topicField.getAttribute("value"));
        assertTrue(topicCommit.isDisplayed());
        topicCommit.click();
        Selenide.sleep(1000);
        int caseNumber = totalCount();
        System.out.println("Number of cases: " + caseNumber);
        assertTrue(issuesAll.isDisplayed());
        issuesAll.click();
        assertTrue(searcherQuery.isDisplayed());
        searcherQuery.setValue("TestSeleniumATHomework");
        assertEquals("TestSeleniumATHomework", searcherQuery.getAttribute("value"));
        assertTrue(searchCommit.isDisplayed());
        searchCommit.click();
        Selenide.sleep(1000);
        assertTrue(statusCheck.isDisplayed());
        Assertions.assertEquals("СДЕЛАТЬ", statusCheck.getText());
        assertTrue(versionCheck.isDisplayed());
        Assertions.assertEquals("Version 2.0", versionCheck.getText());
        assertTrue(taskNew.isDisplayed());
        taskNew.click();
        Selenide.sleep(2000);
        assertTrue(modeSelect.isDisplayed());
        modeSelect.click();
        topicField.setValue("HW" + (caseNumber + 1));
        assertEquals(("HW" + (caseNumber + 1)), topicField.getAttribute("value"));
        assertTrue(descriptionField.isDisplayed());
        descriptionField.click();
        Selenide.switchTo().frame(descriptionField);
        descriptionInput.setValue("Im a chill little entry");
        assertEquals("Im a chill little entry", descriptionInput.getText());
        Selenide.switchTo().defaultContent();
        assertTrue(versionSelect.isDisplayed());
        versionSelect.selectOptionByValue("10001");
        assertEquals("Version 2.0", versionSelect.getSelectedOptionText());
        tagField.scrollIntoView(true);
        assertTrue(tagField.isDisplayed());
        tagSuggest.click();
        Selenide.sleep(1000);
        assertTrue(tagSelectBugFix.isDisplayed());
        tagSelectBugFix.click();
        assertTrue(issueSuggest.isDisplayed());
        issueSuggest.click();
        Selenide.sleep(1000);
        assertTrue(issueSelect.isDisplayed());
        issueSelect.click();
        assertTrue(epicSuggest.isDisplayed());
        epicSuggest.click();
        Selenide.sleep(1000);
        assertTrue(epicSelect.isDisplayed());
        epicSelect.click();
        assertTrue(sprintSuggest.isDisplayed());
        sprintSuggest.click();
        Selenide.sleep(1000);
        assertTrue(sprintSelect.isDisplayed());
        sprintSelect.click();
        assertTrue(topicCommit.isDisplayed());
        topicCommit.click();
        assertTrue(quickSearchInput.isDisplayed());
        Selenide.sleep(1000);
        quickSearchInput.setValue("HW" + (caseNumber + 1)).pressEnter();
        Selenide.sleep(1000);
        assertTrue(issueInWork.isDisplayed());
        issueInWork.click();
        Selenide.sleep(500);
        assertTrue(issueTransition.isDisplayed());
        issueTransition.click();
        Selenide.sleep(500);
        assertTrue(issueFinished.isDisplayed());
        issueFinished.click();
        Selenide.sleep(500);
        assertTrue(issueFinishedSubmit.isDisplayed());
        issueFinishedSubmit.click();
        Selenide.sleep(500);
        assertTrue(issueTransition.isDisplayed());
        issueTransition.click();
        Selenide.sleep(500);
        assertTrue(issueCompleted.isDisplayed());
        issueCompleted.click();
    }
}
