package ru.iFellow.FifthLesson;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Scanner;

public class EpisodeData {
    private static final String API_URL = "https://rickandmortyapi.com/api";
    private String episodeInput;
    private List<String> episodes;
    private JsonPath lastEpisodeData;

    public void episodeRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the full episode code:");
        this.episodeInput = scanner.nextLine();
        System.out.println("Searching for episode: " + episodeInput);
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

}