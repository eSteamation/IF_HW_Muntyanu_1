package ru.iFellow.FifthLesson;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import ru.iFellow.FifthLesson.Hook.Hook;
import ru.iFellow.FifthLesson.PartOne.Data.CharacterData;
import ru.iFellow.FifthLesson.PartOne.Data.EpisodeData;
import ru.iFellow.FifthLesson.PartTwo.JsonInteractions;
import utils.MockScanner;

import java.util.Scanner;


public class MainTest extends Hook {
    protected JsonInteractions jsonInteractions = new JsonInteractions();
    protected EpisodeData episodeData = new EpisodeData();
    protected CharacterData characterData = new CharacterData();

    @Test
    public void testCreateObject() {
        jsonInteractions.jsonImport("ObjectTemplate.json");
        jsonInteractions.jsonModifier("Tomato", "Eat market");
        jsonInteractions.jsonRequest("201");
        jsonInteractions.jsonVerify("Tomato", "Eat market");
    }

    @Test
    public void testRickAndMortyByCharacter() {
        Scanner mockScanner = MockScanner.createMockScanner("Morty Smith");
        characterData = new CharacterData(mockScanner);
        characterData.nameRequest();
        JsonPath characterResponse = characterData.currentCharacterData();
        episodeData.getEpisodeList(characterResponse);
        characterData.characterRecorder();
        episodeData.getLastEpisodeData();
        episodeData.episodeVerify();
    }

    @Test
    public void testRickAndMortyByEpisode() {
        Scanner mockScanner = MockScanner.createMockScanner("S05E10");
        episodeData = new EpisodeData(mockScanner);
        episodeData.episodeRequest();
        characterData.characterGetList(episodeData.currentEpisodeData());
        characterData.characterListVerify();
    }

    @Test
    public void testRickAndMortyCompareCharacters() {
        testRickAndMortyByCharacter();
        testRickAndMortyByEpisode();
        characterData.lastCharacter();
        characterData.characterComparator();
        characterData.characterComparisonVerify();
    }
}