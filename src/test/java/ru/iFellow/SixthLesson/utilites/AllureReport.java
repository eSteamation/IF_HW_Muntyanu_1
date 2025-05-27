package ru.iFellow.SixthLesson.utilites;

import io.qameta.allure.ConfigurationBuilder;
import io.qameta.allure.ReportGenerator;
import io.qameta.allure.core.Configuration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class AllureReport {

    public static void generateReport() {
        Configuration configuration = ConfigurationBuilder.bundled().build();
        Path sourceDir = Paths.get("target/allure-results");
        Path outputFile = Paths.get("target/allure-reports");
        new ReportGenerator(configuration).generateSingleFile(outputFile, Collections.singletonList(sourceDir));
    }

    public static void openAllureReport() {
        File reportFolder = new File("target/allure-reports");
        File indexFile = new File(reportFolder, "index.html");
        try {
            Desktop.getDesktop().open(indexFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
