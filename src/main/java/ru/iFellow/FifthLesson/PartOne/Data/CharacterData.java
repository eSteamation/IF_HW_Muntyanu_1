package ru.iFellow.FifthLesson.PartOne.Data;

import com.google.common.base.Objects;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.Getter;
import ru.iFellow.FifthLesson.PartOne.Utils.ComparisonResult;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CharacterData {
    private static final String API_URL = "https://rickandmortyapi.com/api";
    private List<String> characters;
    private JsonPath lastCharacterData;
    private ComparisonResult resultLocation;
    private ComparisonResult resultSpecies;
    @Getter
    private String nameInput;
    private Scanner scanner;
    private String charOneLocation;
    private String charOneSpecies;

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
        System.out.println("Enter the full name of the character:");
        this.nameInput = scanner.nextLine();
        System.out.println("Searching for character: " + nameInput);
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
        System.out.println("Location comparison result: " + resultLocation);
        resultSpecies = characterComparatorTest(charOneSpecies, charTwoSpecies);
        System.out.println("Species comparison result: " + resultSpecies);
        System.out.println("Location of the first character: " + charOneLocation + "\nLocation of the second character: " + charTwoLocation);
        System.out.println("Species of the first character: " + charOneSpecies + "\nSpecies of the second character: " + charTwoSpecies);
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
        System.out.println("Last character in the episode:");
        System.out.println("Name: " + lastCharacterData.getString("name"));
        System.out.println("Species: " + lastCharacterData.getString("species"));
        System.out.println("Location: " + lastCharacterData.getString("location.name"));
    }

    public void characterComparisonVerify() {
        assertNotNull(resultSpecies);
        assertNotEquals(ComparisonResult.ERROR, resultSpecies);
        assertNotNull(resultLocation);
        assertNotEquals(ComparisonResult.ERROR, resultLocation);
    }
}