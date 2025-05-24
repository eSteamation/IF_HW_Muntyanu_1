package ru.iFellow.FifthLesson.PartTwo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonInteractions {
    protected Map<String, Object> requestBody;
    protected JsonPath jsonPath;
    protected Response response;
    private String API_KEY;

    public void jsonImport(String jsonName) {
        InputStream jsonStream = getClass().getClassLoader()
                .getResourceAsStream(jsonName);
        jsonPath = new JsonPath(jsonStream);
        assertNotNull(jsonStream);
    }

    public void setup() {
        RestAssured.baseURI = utils.ConfigReader.getProperty("API_JSON");
        API_KEY = utils.ConfigReader.getProperty("API_KEY");
    }

    public void jsonModifier(String name, String job) {
        requestBody = jsonPath.getMap("$");
        requestBody.put("name", name);
        requestBody.put("job", job);
    }

    public void jsonRequest(String expectedCode) {
        setup();
        response = RestAssured.given()
                .header("X-API-Key", API_KEY)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(Integer.parseInt(expectedCode))
                .extract()
                .response();
    }

    public void jsonVerify(String name, String job) {
        JsonPath responseJson = response.jsonPath();
        assertEquals(name, responseJson.getString("name"));
        assertEquals(job, responseJson.getString("job"));
    }

}
