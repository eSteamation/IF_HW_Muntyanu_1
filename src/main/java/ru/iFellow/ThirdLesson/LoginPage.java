package ru.iFellow.ThirdLesson;

import com.codeborne.selenide.SelenideElement;
import utils.UtilsConfig;
import utils.UtilsWait;

import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage {
    protected final SelenideElement usernameField = $x("//input[@name='os_username']").as("Поле никнейма");
    protected final SelenideElement passwordField = $x("//input[@type='password']").as("Поле пароля");
    protected final SelenideElement loginCommit = $x("//div[@class='buttons']//input[@name='login']").as("Вход");

    public void login() {
        usernameField.setValue(UtilsConfig.getProperty("username"));
        passwordField.setValue(UtilsConfig.getProperty("password"));
        loginCommit.click();
    }

    public void loginWait() {
        UtilsWait.waitFor(visibilityOf(usernameField));
        UtilsWait.waitFor(visibilityOf(passwordField));
        UtilsWait.waitFor(visibilityOf(loginCommit));
    }
}
