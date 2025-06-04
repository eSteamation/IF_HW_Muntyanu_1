package ru.iFellow.API.PartTwo.Steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.iFellow.API.PartTwo.JsonInteractions;

public class JsonInteractionsStep {
    protected JsonInteractions jsonInteractions = new JsonInteractions();

    @Дано("Образец файла загружен")
    public void importing() {
        jsonInteractions.jsonImport("ObjectTemplate.json");
    }

    @Когда("Меняем JSON, заменяя его имя на {string}, и добавляя параметр job с значением {string}")
    public void modifying(String name, String job) {
        jsonInteractions.jsonModifier(name, job);
    }

    @Тогда("Отправляет запрос с модифицированным JSON")
    public void sentRequest() {
        jsonInteractions.jsonRequest("201");
    }

    @И("Проверяем, что полученное имя {string}, а job {string}.")
    public void verification(String name, String job) {
        jsonInteractions.jsonVerify(name, job);
    }
}