package ru.iFellow.API.PartOne.Steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.restassured.path.json.JsonPath;
import ru.iFellow.API.PartOne.Data.CharacterData;
import ru.iFellow.API.PartOne.Data.EpisodeData;
import ru.iFellow.API.Utils.MockScanner;
import ru.iFellow.API.Utils.SharedContext;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EpisodeStep {
    protected EpisodeData episodeData = new EpisodeData();
    protected CharacterData characterData = new CharacterData();

    @Дано("Выбрали эпизод {string} для сбора информации")
    @И("выбрали эпизод {string} для сбора информации")
    public void episodeCodeInput(String episodeCode) {
        Scanner mockScanner = MockScanner.createMockScanner(episodeCode);
        episodeData = new EpisodeData(mockScanner);
        episodeData.episodeRequest();
    }

    @Когда("Запрашиваем список персонажей выбранного эпизода")
    public void dataRequest() {
        JsonPath episodeResponse = episodeData.currentEpisodeData();
        characterData.characterGetList(episodeResponse);
        SharedContext.lastEpisodeResponse = episodeResponse;
    }

    @И("Проверяем успешность запроса")
    public void dataVerify() {
        characterData.characterListVerify();
    }

    @И("Проверяем информацию о персонаже")
    public void lastCharacterVerify() {
        JsonPath lastCharacter = characterData.getLastCharacter(SharedContext.lastEpisodeResponse);
        assertNotNull(lastCharacter.getString("name"));
        assertNotNull(lastCharacter.getString("species"));
        assertNotNull(lastCharacter.getString("location.name"));
    }
}
