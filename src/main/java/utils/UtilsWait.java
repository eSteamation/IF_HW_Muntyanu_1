package utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UtilsWait {
    private static final long DEFAULT_TIMEOUT = 15;

    public static void waitFor(ExpectedCondition<?> condition) {
        waitFor(condition, DEFAULT_TIMEOUT);
    }

    public static void waitFor(ExpectedCondition<?> condition, long timeoutSeconds) {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(condition);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при ожидании условия: " + condition, e);
        }
    }

}