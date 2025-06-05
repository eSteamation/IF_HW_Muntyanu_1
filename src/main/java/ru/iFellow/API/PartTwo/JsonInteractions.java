package ru.iFellow.API.PartTwo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import ru.iFellow.API.Utils.SharedContext;

import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonInteractions {
    protected Map<String, Object> requestBody;
    protected JsonPath jsonPath;
    protected Response response;

    public void jsonImport(String jsonName) {
        InputStream jsonStream = getClass().getClassLoader()
                .getResourceAsStream(jsonName);
        jsonPath = new JsonPath(jsonStream);
        assertNotNull(jsonStream);
    }

    public void jsonModifier(String name, String job) {
        requestBody = jsonPath.getMap("$");
        requestBody.put("name", name);
        requestBody.put("job", job);
    }

    public void jsonRequest(String expectedCode) {
        response = RestAssured.given()
                .header("X-API-Key", SharedContext.API_KEY)
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
