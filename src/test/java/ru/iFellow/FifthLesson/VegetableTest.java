package ru.iFellow.FifthLesson;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.iFellow.FifthLesson.PartTwo.JsonInteractions;

public class VegetableTest {
    protected JsonInteractions jsonInteractions = new JsonInteractions();

    @Test
    @DisplayName("Модификация объекта и проверка результата")
    public void testCreateObject() {
        jsonInteractions.jsonImport("ObjectTemplate.json");
        jsonInteractions.jsonModifier("Tomato", "Eat market");
        jsonInteractions.jsonRequest("201");
        jsonInteractions.jsonVerify("Tomato", "Eat market");
    }
}