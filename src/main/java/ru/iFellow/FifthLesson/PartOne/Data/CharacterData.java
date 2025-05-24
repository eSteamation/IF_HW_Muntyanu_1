package ru.iFellow.FifthLesson.PartOne.Data;

import com.google.common.base.Objects;
import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iFellow.FifthLesson.PartOne.Utils.ComparisonResult;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class CharacterData {

    private static final String API_URL = "";
    private List<String> characters;
    private JsonPath lastCharacterData;
    private ComparisonResult resultLocation;
    private ComparisonResult resultSpecies;
    @Getter
    private String nameInput;
    private Scanner scanner;
    private String charOneLocation;
    private String charOneSpecies;
    private static final Logger logger = LoggerFactory.getLogger(CharacterData.class);

    public CharacterData() {
        this(new Scanner(System.in));
    }

    public CharacterData(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setup() {
        RestAssured.baseURI = utils.ConfigReader.getProperty("API_RNM");
    }

    public static ComparisonResult characterComparatorTest(Object obj1, Object obj2) {
        try {
            boolean equal = Objects.equal(obj1, obj2);
            return equal ? ComparisonResult.EQUAL : ComparisonResult.DIFFERENT;
        } catch (Exception e) {
            return ComparisonResult.ERROR;
        }
    }

    public void nameRequest() {
        setup();
        logger.info("Asks for the full name of the character");
        this.nameInput = scanner.nextLine();
        logger.info("Searching for the character: {}", nameInput);
    }

    public JsonPath currentCharacterData() {
        return RestAssured.given()
                .queryParam("name", nameInput)
                .get(API_URL + "/character")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
    }

    public void characterRecorder() {
        JsonPath characterData = currentCharacterData();
        charOneLocation = characterData.getString("results[0].location.name");
        charOneSpecies = characterData.getString("results[0].species");
    }

    public void characterComparator() {
        characterRecorder();
        String charTwoLocation = lastCharacterData.getString("location.name");
        String charTwoSpecies = lastCharacterData.getString("species");
        resultLocation = characterComparatorTest(charOneLocation, charTwoLocation);
        logger.info("Location comparison result: {}", resultLocation);
        resultSpecies = characterComparatorTest(charOneSpecies, charTwoSpecies);
        logger.info("Species comparison result: {}", resultSpecies);
        logger.info("Location of the first character: {}\nLocation of the second character: {}", charOneLocation, charTwoLocation);
        logger.info("Species of the first character: {}\nSpecies of the second character: {}", charOneSpecies, charTwoSpecies);
    }

    public void characterGetList(JsonPath episodeResponse) {
        this.characters = episodeResponse.getList("results[0].characters");
    }

    public void characterListVerify() {
        assertNotNull(this.characters);
    }

    public void lastCharacter() {
        String lastCharacterUrl = characters.get(characters.size() - 1);
        this.lastCharacterData = RestAssured.given()
                .get(lastCharacterUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
    }

    public void characterComparisonVerify() {
        assertNotNull(resultSpecies);
        assertNotEquals(ComparisonResult.ERROR, resultSpecies);
        assertNotNull(resultLocation);
        assertNotEquals(ComparisonResult.ERROR, resultLocation);
    }

    public void logLastCharacterInfo() {
        logger.info("Last character in the episode:");
        logger.info("Name: {}", lastCharacterData.getString("name"));
        logger.info("Species: {}", lastCharacterData.getString("species"));
        logger.info("Location: {}", lastCharacterData.getString("location.name"));
    }
}

