# JIRA автотесты

Проект представляет собой автоматизированные тесты для веб-приложения.

# Использованные технологии

- **Java 17** – основной язык разработки
- **Selenide** – фреймворк для автоматизации веб-тестов
- **Allure Report** – генерация детализированных отчётов
- **JUnit 5** – организация тестовых сценариев
- **Maven** – управление зависимостями и сборка проекта

Версию с использованием **Cucumber** можно посмотреть в ветке HW4.

# Пререквизиты

[Maven 3.9.9](https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip)

[JDK 17.0.12](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

Необходимо добавить их %_framework_name_%/bin/ в PATH.

# Потенциальные проблемы \ изменения

Можно использовать следующий код (**вместо** существующего) в AllureReportService для того, чтобы отчет открывался сразу
же по завершению тестов, но он всегда приводит к тому, что отчет добавляет лишнюю ошибку, не связанную с тестами. Найти
решение этой проблеме я еще не успел. mvn clean test allure::serve так же открывает две инстанции репорта в этом случае.

```
    public static void generateReport() {
        Configuration configuration = ConfigurationBuilder.bundled().build();
//        List<Path> sourcePaths = new ArrayList<>();


        Path sourcePath = Paths.get("target/allure-results");
        Path destinationPath = Paths.get("target/allure-report");
//        sourcePaths.add(sourcePath);

        new ReportGenerator(configuration).generateSingleFile(destinationPath, sourcePath);
    }


    public static void openAllureReport() {
        try {
            File report = new File("target/allure-report/index.html");

                Desktop.getDesktop().browse(report.toURI());

            } catch (IOException e) {
            throw new RuntimeException(e);
        }
```