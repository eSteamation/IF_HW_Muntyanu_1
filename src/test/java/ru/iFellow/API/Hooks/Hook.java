package ru.iFellow.API.Hooks;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import ru.iFellow.API.Utils.ConfigReader;
import ru.iFellow.API.Utils.SharedContext;

public class Hook {

    @Before("@RNM")
    public void rickandmortySetup() {
        RestAssured.baseURI = ConfigReader.getProperty("API_RNM");
    }

    @Before("@Veggies")
    public void veggiesSetup() {
        RestAssured.baseURI = ConfigReader.getProperty("API_JSON");
        SharedContext.API_KEY = ConfigReader.getProperty("API_KEY");
    }
}