package ru.iFellow.API.PartOne.Data;

import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class EpisodeData {
    private static final String API_URL = "https://rickandmortyapi.com/api";
    private static final Logger logger = LoggerFactory.getLogger(EpisodeData.class);
    private String episodeInput;
    private List<String> episodes;
    private final Scanner scanner;
    private JsonPath lastEpisodeData;


    public EpisodeData(Scanner scanner) {
        this.scanner = scanner;
    }

    public EpisodeData() {
        this(new Scanner(System.in));
    }

    public void episodeRequest() {
        logger.info("Asking for the full episode code.");
        this.episodeInput = scanner.nextLine();
        logger.info("Searching for episode: {}", episodeInput);
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

    public JsonPath getLastEpisodeData() {
        String lastEpisodeUrl = episodes.get(episodes.size() - 1);
        lastEpisodeData = RestAssured.given()
                .get(lastEpisodeUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        return lastEpisodeData;
    }

    public void episodeVerify() {
        assertNotNull(lastEpisodeData.getString("name"));
        assertNotNull(lastEpisodeData.getString("episode"));
    }

}