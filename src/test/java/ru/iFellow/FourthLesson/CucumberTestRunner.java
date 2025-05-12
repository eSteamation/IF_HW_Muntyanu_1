package ru.iFellow.FourthLesson;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameters({
        @ConfigurationParameter(
                key = "cucumber.glue",
                value = "ru.iFellow.FourthLesson"
        ),
        @ConfigurationParameter(
                key = "cucumber.plugin",
                value = "pretty"
        )
})

public class CucumberTestRunner {
}