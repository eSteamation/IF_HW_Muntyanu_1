package ru.iFellow.FifthLesson;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.iFellow.FifthLesson.PartOne.Data.CharacterData;
import ru.iFellow.FifthLesson.PartOne.Data.EpisodeData;

import java.util.Scanner;

public class RickAndMortyTest {
    protected EpisodeData episodeData = new EpisodeData();
    protected CharacterData characterData = new CharacterData();

    @Test
    @DisplayName("Поиск и пулл последнего эпизода у выбранного персонажа")
    public void testRickAndMortyByCharacter() {
        Scanner mockScanner = utils.MockScanner.createMockScanner("Morty Smith");
        characterData = new CharacterData(mockScanner);
        characterData.nameRequest();
        JsonPath characterResponse = characterData.currentCharacterData();
        episodeData.getEpisodeList(characterResponse);
        characterData.characterRecorder();
        episodeData.getLastEpisodeData();
        episodeData.episodeVerify();
        episodeData.logLastEpisodeInfo();
    }

    @Test
    @DisplayName("Поиск и пулл последнего персонажа из выбранного эпизода")
    public void testRickAndMortyByEpisode() {
        Scanner mockScanner = utils.MockScanner.createMockScanner("S05E10");
        episodeData = new EpisodeData(mockScanner);
        episodeData.episodeRequest();
        characterData.characterGetList(episodeData.currentEpisodeData());
        characterData.characterListVerify();
        characterData.lastCharacter();
        characterData.logLastCharacterInfo();
    }

    @Test
    @DisplayName("Сравнение персонажей")
    public void testRickAndMortyCompareCharacters() {
        testRickAndMortyByCharacter();
        testRickAndMortyByEpisode();
        characterData.characterComparator();
        characterData.characterComparisonVerify();
    }
}
