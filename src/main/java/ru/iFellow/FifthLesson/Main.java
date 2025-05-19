package ru.iFellow.FifthLesson;

import io.restassured.path.json.JsonPath;

public class Main {
    protected static CharacterData characterData = new CharacterData();
    protected static EpisodeData episodeData = new EpisodeData();

    public static void main(String[] args) {
        characterData.nameRequest();
        JsonPath characterResponse = characterData.currentCharacterData();
        episodeData.getEpisodeList(characterResponse);
        characterData.characterRecorder();
        episodeData.getLastEpisodeData();
        episodeData.printLastEpisodeInfo(characterData.getNameInput());
        episodeData.episodeRequest();
        characterData.getCharacterList(episodeData.currentEpisodeData());
        characterData.lastCharacter();
        characterData.characterComparator();
    }
}