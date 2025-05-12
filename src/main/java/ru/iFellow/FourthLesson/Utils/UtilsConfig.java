package ru.iFellow.FourthLesson.Utils;

import java.io.InputStream;
import java.util.Properties;

public class UtilsConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = UtilsConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки конфига", e);
        }
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }
}