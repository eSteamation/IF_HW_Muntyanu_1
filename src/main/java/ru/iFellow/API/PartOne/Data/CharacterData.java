package ru.iFellow.API.PartOne.Data;

import com.google.common.base.Objects;
import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iFellow.API.Utils.ComparisonResult;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class CharacterData {

    protected static final String API_URL = "";
    protected static final Logger logger = LoggerFactory.getLogger(CharacterData.class);
    protected final Scanner scanner;
    protected List<String> characters;
    @Getter
    protected String nameInput;

    public CharacterData() {
        this(new Scanner(System.in));
    }

    public CharacterData(Scanner scanner) {
        this.scanner = scanner;
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
        this.nameInput = scanner.nextLine();
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

    public void characterGetList(JsonPath episodeResponse) {
        this.characters = episodeResponse.getList("results[0].characters");
    }

    public void characterListVerify() {
        assertNotNull(this.characters);
    }

    public JsonPath getLastCharacter(JsonPath episodeResponse) {
        List<String> characters = episodeResponse.getList("results[0].characters");
        assertNotNull(characters, "Список персонажей не должен быть null");
        assertFalse(characters.isEmpty(), "Список персонажей не должен быть пустым");

        String lastCharacterUrl = characters.get(characters.size() - 1);
        return RestAssured.given()
                .get(lastCharacterUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
    }
}
