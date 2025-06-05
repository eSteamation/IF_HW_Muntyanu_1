package ru.iFellow.API;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import ru.iFellow.API.Utils.SharedContext;

import java.util.List;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class RunCucumberTest {

    @BeforeAll
    public static void globalSetup() {
        LogConfig logConfig = LogConfig.logConfig().blacklistHeaders(List.of("X-API-Key"));
        RestAssured.config = RestAssured.config().logConfig(logConfig);
        RestAssured.filters(new AllureRestAssured());
    }

    @AfterAll
    public static void globalTeardown() {
        RestAssured.baseURI = null;
        SharedContext.API_KEY = null;
    }
}