package ru.iFellow.FifthLesson.PartOne.Data;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EpisodeData {
    private static final String API_URL = "https://rickandmortyapi.com/api";
    private Scanner scanner;
    private String episodeInput;
    private List<String> episodes;
    private JsonPath lastEpisodeData;

    public EpisodeData(Scanner scanner) {
        this.scanner = scanner;
    }

    public EpisodeData() {
        this(new Scanner(System.in));
    }


    public void episodeRequest() {
        System.out.println("Enter the full name of the character:");
        this.episodeInput = scanner.nextLine();
        System.out.println("Searching for character: " + episodeInput);
    }

    public JsonPath currentEpisodeData() {
        return RestAssured.given()
                .queryParam("episode", episodeInput)
                .get(API_URL + "/episode")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
    }

    public void getEpisodeList(JsonPath characterResponse) {
        this.episodes = characterResponse.getList("results[0].episode");
    }

    public void getLastEpisodeData() {
        String lastEpisodeUrl = episodes.get(episodes.size() - 1);
        this.lastEpisodeData = RestAssured.given()
                .get(lastEpisodeUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
    }

    public void printLastEpisodeInfo(String characterName) {
        System.out.println("Последний эпизод с: " + characterName);
        System.out.println("Название: " + lastEpisodeData.getString("name"));
        System.out.println("Эпизод: " + lastEpisodeData.getString("episode"));
    }

    public void episodeVerify() {
        assertNotNull(lastEpisodeData.getString("name"));
        assertNotNull(lastEpisodeData.getString("episode"));
    }

}