package ru.iFellow.API.PartOne.Steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.path.json.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iFellow.API.PartOne.Data.CharacterData;
import ru.iFellow.API.PartOne.Data.CharacterParameters;
import ru.iFellow.API.PartOne.Data.EpisodeData;
import ru.iFellow.API.Utils.ComparisonResult;
import ru.iFellow.API.Utils.MockScanner;
import ru.iFellow.API.Utils.SharedContext;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CharacterStep extends CharacterData {
    private static final Logger logger = LoggerFactory.getLogger(CharacterStep.class);
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

    @И("Проверяем и логгируем информацию об эпизоде")
    public void episodeVerification() {
        JsonPath lastEpisode = episodeData.getLastEpisodeData();
        assertNotNull(lastEpisode.getString("name"));
        assertNotNull(lastEpisode.getString("episode"));
        logger.info("Последний эпизод с выбранным персонажем:");
        logger.info("Название эпизода: {}", lastEpisode.getString("name"));
        logger.info("Код эпизода: {}", lastEpisode.getString("episode"));
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
        logger.info("\nИмя первого персонажа: {}\nИмя второго персонажа: {}", firstCharacter.getName(), secondCharacter.getName());
        logger.info("\nРезультат сравнения локаций: {}", resultLocation);
        logger.info("\nРезультат сравнения видов: {}", resultSpecies);
        logger.info("\nЛокация первого персонажа: {}\nЛокация второго персонажа: {}", firstCharacter.getLocation(), secondCharacter.getLocation());
        logger.info("\nВид первого персонажа: {}\nВид второго персонажа: {}", firstCharacter.getSpecies(), secondCharacter.getSpecies());
    }
}
