# Petstore API Autotests

Проект с примером автотестов для Swagger Petstore API.  
Тесты покрывают REST API, основные пользовательские сценарии и e2e цепочки.

Swagger Petstore:  
https://petstore.swagger.io

## Стек
Java 17, Maven 3.9, JUnit 5, RestAssured, Hamcrest  

## Структура проекта

src/test/java/com/example/petstore\n
|-- client // API-клиенты (PetClient, UserClient, StoreClient)
|-- config // Общая конфигурация (baseURI, auth)
|-- data // Фабрики тестовых данных
|-- model // модели (Pet, Order, User и т.д.)
|-- tests
| |-- pet // Тесты Pet API
| |-- user // Тесты User API
| |-- store // Тесты Store API
| |-- e2e // End-to-End сценарии
|-- resources // Изображение для теста

## Инструкция по запуску

Запуск всех тестов проиходит в папке petstore-api-tests: mvn clean test
