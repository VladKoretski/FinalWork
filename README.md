# Дипломная работа QA-middle
## Этап 1. Проверка приложения
* [Plan.md](https://github.com/VladKoretski/FinalWork/blob/main/Plan.md "Описание плана по проверке и автоматизации приложения");
* [Check.xlsx](https://github.com/VladKoretski/FinalWork/blob/main/Check.xlsx "Чек-лист проекта и отметками о пройденых и непройденых тестах");
* [Cases.xlsx](https://github.com/VladKoretski/FinalWork/blob/main/Cases.xlsx "Тест-кейсы")

## Этап 2. Автоматизация проверки приложения  
Настройка проекта перед написанием UI-тестов:  
* [необходимые библиотеки - build.gradle](https://github.com/VladKoretski/FinalWork/blob/main/ApplicationForTest/build.gradle);  
* [директория для тестов](https://github.com/VladKoretski/FinalWork/tree/main/ApplicationForTest/src/androidTest/java/ru/iteco/fmhandroid/ui):
  1. [Константы, генераторы случаныйх текстов, генераторы случайных дат и времени];(https://github.com/VladKoretski/FinalWork/tree/main/ApplicationForTest/src/androidTest/java/ru/iteco/fmhandroid/ui/datasources)
  2. [Действия с управляющими элементами, чеккеры и операции со страницами](https://github.com/VladKoretski/FinalWork/tree/main/ApplicationForTest/src/androidTest/java/ru/iteco/fmhandroid/ui/operations);  
  3. [Тесты](https://github.com/VladKoretski/FinalWork/tree/main/ApplicationForTest/src/androidTest/java/ru/iteco/fmhandroid/ui/tests);  
* [тестовый класс](https://github.com/VladKoretski/FinalWork/tree/main/ApplicationForTest/app/src/androidTest/java/ru/iteco/fmhandroid/ui/tests);  
* UI-тесты:  
  1. [существование элементов на страницах](https://github.com/VladKoretski/FinalWork/blob/main/ApplicationForTest/src/androidTest/java/ru/iteco/fmhandroid/ui/tests/ElementsExistenceTest.java);  
  2. [тесты авторизации](https://github.com/VladKoretski/FinalWork/blob/main/ApplicationForTest/src/androidTest/java/ru/iteco/fmhandroid/ui/tests/AuthorizationPageTest.java);  
  3. [операции с новостями](https://github.com/VladKoretski/FinalWork/blob/main/ApplicationForTest/src/androidTest/java/ru/iteco/fmhandroid/ui/tests/NewsPageFunctionsTest.java);
  4. [переходы со страницы About на сторонние ссылки](https://github.com/VladKoretski/FinalWork/blob/main/ApplicationForTest/src/androidTest/java/ru/iteco/fmhandroid/ui/tests/AboutPageLinksTest.java);
  5. [тестирование главного меню](https://github.com/VladKoretski/FinalWork/blob/main/ApplicationForTest/src/androidTest/java/ru/iteco/fmhandroid/ui/tests/MainMenuTest.java);
  6. [переходы на страницу Наша миссия](https://github.com/VladKoretski/FinalWork/blob/main/ApplicationForTest/src/androidTest/java/ru/iteco/fmhandroid/ui/tests/OurMissionPageTest.java)  
* [allure report](https://github.com/VladKoretski/FinalWork/tree/main/report)   
  
## Этап 3.

