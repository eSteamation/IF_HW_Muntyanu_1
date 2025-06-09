package ru.iFellow.API.PartOne.Steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.path.json.JsonPath;
import ru.iFellow.API.PartOne.Data.CharacterData;
import ru.iFellow.API.PartOne.Data.CharacterParameters;
import ru.iFellow.API.PartOne.Data.EpisodeData;
import ru.iFellow.API.Utils.ComparisonResult;
import ru.iFellow.API.Utils.MockScanner;
import ru.iFellow.API.Utils.SharedContext;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static io.qameta.allure.Allure.addAttachment;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CharacterStep extends CharacterData {
    protected CharacterParameters firstCharacter = null;
    protected CharacterParameters secondCharacter = null;
    protected EpisodeData episodeData = new EpisodeData();
    protected CharacterData characterData = new CharacterData();
    protected JsonPath characterResponse = null;

    @Дано("Выбрали персонажа {string} для сбора информации")
    public void characterInput(String characterName) {
        Scanner mockScanner = MockScanner.createMockScanner(characterName);
        characterData = new CharacterData(mockScanner);
        characterData.nameRequest();
    }

    @Когда("Запрашиваем данные персонажа")
    public void dataRequest() {
        characterResponse = characterData.currentCharacterData();
    }

    @Когда("Запрашиваем данные последнего персонажа в списке")
    public void lastCharacterRequest() {
        characterResponse = characterData.getLastCharacter(SharedContext.lastEpisodeResponse);
    }

    @И("Проверяем список эпизодов")
    public void episodeList() {
        episodeData.getEpisodeList(characterResponse);
    }

    @Тогда("Находим последний эпизод, в котором участвовал персонаж")
    public void episodeLast() {
        episodeData.getLastEpisodeData();
        episodeData.episodeVerify();
    }

    @И("Проверяем информацию об эпизоде")
    public void episodeVerification() {
        JsonPath lastEpisode = episodeData.getLastEpisodeData();
        assertNotNull(lastEpisode.getString("name"));
        assertNotNull(lastEpisode.getString("episode"));
    }

    @И("Запоминаем параметры 'Местонахождение' и 'Вид' для сравнения")
    public void parametersRecorder() {
        String name;
        String location;
        String species;
        if (firstCharacter == null) {
            name = characterResponse.getString("results[0].name");
            location = characterResponse.getString("results[0].location.name");
            species = characterResponse.getString("results[0].species");
            firstCharacter = new CharacterParameters(name, location, species);
        } else {
            name = characterResponse.getString("name");
            location = characterResponse.getString("location.name");
            species = characterResponse.getString("species");
            secondCharacter = new CharacterParameters(name, location, species);
        }
    }

    @Тогда("Сравниваем поля 'Вид' и 'Местонахождение' двух персонажей")
    public void comparator() {
        ComparisonResult resultLocation = characterComparatorTest(firstCharacter.getLocation(), secondCharacter.getLocation());
        ComparisonResult resultSpecies = characterComparatorTest(firstCharacter.getSpecies(), secondCharacter.getSpecies());
        String logData = "Персонаж 1:\n" + " - Раса: " + firstCharacter.getSpecies() + "\n" + " - Локация: " + firstCharacter.getLocation() + "\n\n" +
                "Персонаж 2:\n" + " - Раса: " + secondCharacter.getSpecies() + "\n" + " - Локация: " + secondCharacter.getLocation() + "\n\n" +
                "Результат сравнения:\n" + " - Вид: " + resultSpecies + "\n" + " - Местонахождение: " + resultLocation;
        addAttachment("Результаты сравнения персонажей", new ByteArrayInputStream(logData.getBytes(StandardCharsets.UTF_8)));
    }
}
