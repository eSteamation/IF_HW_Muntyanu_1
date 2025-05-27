package ru.iFellow.ThirdLesson;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.UtilsConfig;
import utils.UtilsWait;

import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage {
    protected final SelenideElement usernameField = $x("//input[@name='os_username']").as("Поле ввода логина");
    protected final SelenideElement passwordField = $x("//input[@type='password']").as("Поле ввода пароля");
    protected final SelenideElement loginCommit = $x("//div[@class='buttons']//input[@name='login']").as("Кнопка входа");

    @Step("Выполнение входа")
    public void login() {
        step("Ввод логина", () -> usernameField.setValue(UtilsConfig.getProperty("username")));
        step("Ввод пароля", () -> passwordField.setValue(UtilsConfig.getProperty("password")));
        step("Нажатие кнопки входа", () -> loginCommit.click());
    }

    @Step("Ожидание элементов формы входа")
    public void loginWait() {
        step("Ожидание поля логина", () -> UtilsWait.waitFor(visibilityOf(usernameField)));
        step("Ожидание поля пароля", () -> UtilsWait.waitFor(visibilityOf(passwordField)));
        step("Ожидание кнопки входа", () -> UtilsWait.waitFor(visibilityOf(loginCommit)));
    }
}