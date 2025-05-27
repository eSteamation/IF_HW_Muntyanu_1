package ru.iFellow.SixthLesson;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.UtilsConfig;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

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
        step("Ожидание поля логина", () -> usernameField.shouldBe(visible));
        step("Ожидание поля пароля", () -> passwordField.shouldBe(visible));
        step("Ожидание кнопки входа", () -> loginCommit.shouldBe(interactable));
    }
}