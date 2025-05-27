package ru.iFellow.SixthLesson.utilites;

import io.qameta.allure.ConfigurationBuilder;
import io.qameta.allure.ReportGenerator;
import io.qameta.allure.core.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AllureReport {

    public static void generateReport() {
        Configuration configuration = ConfigurationBuilder.bundled().build();
        Path sourcePath = Paths.get("target/allure-results");
        Path destinationPath = Paths.get("target/allure-report");
        new ReportGenerator(configuration).generate(destinationPath, sourcePath);
    }
}