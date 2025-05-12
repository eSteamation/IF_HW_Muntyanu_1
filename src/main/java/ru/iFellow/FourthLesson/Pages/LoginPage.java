package ru.iFellow.FourthLesson.Pages;

import com.codeborne.selenide.SelenideElement;
import ru.iFellow.FourthLesson.Utils.UtilsConfig;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    protected final SelenideElement usernameField = $x("//input[@name='os_username']").as("Поле никнейма");
    protected final SelenideElement passwordField = $x("//input[@type='password']").as("Поле пароля");
    protected final SelenideElement loginCommit = $x("//div[@class='buttons']//input[@name='login']").as("Вход");

    public void loginCredentials() {
        usernameField.setValue(UtilsConfig.getUsername());
        passwordField.setValue(UtilsConfig.getPassword());
    }

    public void loginClick() {
        loginCommit.click();
    }

    public void loginWait() {
        usernameField.shouldBe(visible);
        passwordField.shouldBe(visible);
        loginCommit.shouldBe(interactable);
    }
}
