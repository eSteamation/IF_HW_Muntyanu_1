package ru.iFellow.FifthLesson;

import com.google.common.base.Objects;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.Getter;

import java.util.List;
import java.util.Scanner;

public class CharacterData {
    private static final String API_URL = "https://rickandmortyapi.com/api";
    private List<String> characters;
    private JsonPath lastCharacterData;
    @Getter
    private String nameInput;
    private String charOneLocation;
    private String charOneSpecies;

    public static ComparisonResult characterComparatorTest(Object obj1, Object obj2) {
        boolean equal = Objects.equal(obj1, obj2);
        return equal ? ComparisonResult.EQUAL : ComparisonResult.DIFFERENT;
    }

    public void nameRequest() {
        Scanner scanner = new Scanner(System.in);
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
        ComparisonResult result = characterComparatorTest(charOneLocation, charTwoLocation);
        System.out.println("Результат сравнения локаций: " + result);
        result = characterComparatorTest(charOneSpecies, charTwoSpecies);
        System.out.println("Результат сравнения рас: " + result);
        System.out.println("Локация первого: " + charOneLocation + "\nЛокация второго: " + charTwoLocation);
        System.out.println("Раса первого: " + charOneSpecies + "\nРаса второго: " + charTwoSpecies);
    }

    public void getCharacterList(JsonPath episodeResponse) {
        this.characters = episodeResponse.getList("results[0].characters");
    }

    public void lastCharacter() {
        String lastCharacterUrl = characters.get(characters.size() - 1);
        this.lastCharacterData = RestAssured.given()
                .get(lastCharacterUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        System.out.println("Последний персонаж в эпизоде:");
        System.out.println("Имя: " + lastCharacterData.getString("name"));
        System.out.println("Вид: " + lastCharacterData.getString("species"));
        System.out.println("Локация: " + lastCharacterData.getString("location.name"));
    }

    public enum ComparisonResult {
        EQUAL,
        DIFFERENT
    }
}