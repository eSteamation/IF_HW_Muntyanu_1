package ru.iFellow.UI;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Param;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.SetValueOptions.withText;
import static io.qameta.allure.model.Parameter.Mode.MASKED;


public class LoginPage {
    protected final SelenideElement usernameField = $x("//input[@name='os_username']").as("Поле ввода логина");
    protected final SelenideElement passwordField = $x("//input[@type='password']").as("Поле ввода пароля");
    protected final SelenideElement loginCommit = $x("//div[@class='buttons']//input[@name='login']").as("Кнопка входа");

    @Step("Выполнение входа под логином {username}")
    public void login(String username, @Param(mode = MASKED) String password) {
        usernameField.setValue(username);
        setPassword(password);
        loginCommit.click();
    }

    @Step("Ожидание элементов формы входа")
    public void loginWait() {
        usernameField.shouldBe(visible);
        passwordField.shouldBe(visible);
        loginCommit.shouldBe(interactable);
    }

    @Step("Ввод пароля: *****")
    public void setPassword(@Param(mode = MASKED) String password) {
        passwordField.setValue(withText(password).sensitive());

    }
}