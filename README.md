# Загрузчик изображений NASA APOD

Это Java-приложение загружает "Астрономическое изображение дня" (APOD) из API NASA.

## Описание

Эта программа получает URL изображения из API NASA APOD и загружает изображение в корневой каталог проекта. Она использует библиотеку Apache HttpClient для выполнения HTTP-запросов и Jackson для разбора JSON-ответа от API.

## Функциональность

*   Загрузка астрономического изображения дня.
*   Обработка возможных ошибок формата URL.
*   Использование конфигурационного файла для управления ключом API NASA.

## Необходимые условия

*   Java Development Kit (JDK) версии 17 или выше.
*   Инструмент сборки Gradle.
*   Ключ API NASA (его можно бесплатно получить здесь: [https://api.nasa.gov/](https://api.nasa.gov/)).

## Настройка

1.  Клонируйте репозиторий:

    ```bash
    git clone <repository_url>
    ```

    Замените `<repository_url>` на URL вашего репозитория.
2.  Получите ключ API NASA на [https://api.nasa.gov/](https://api.nasa.gov/).
3.  Откройте файл `NasaApodDownloader.java` и замените `"VJlkY8o71ql3ge1MX0cxqN1fkHYLLdN3fnndKbFQ"` на свой ключ API.
4.  Соберите проект с помощью Gradle:

    ```bash
    ./gradlew build
    ```
5.  Запустите приложение:

    ```bash
    ./gradlew run
    ```

## Использование

После запуска приложения изображение дня будет загружено в корневой каталог проекта.

## Зависимости

*   org.apache.httpcomponents:httpclient:4.5.15
*   com.fasterxml.jackson.core:jackson-databind:2.17.0

## Лицензия

Этот проект распространяется под лицензией MIT - подробности см. в файле [LICENSE](LICENSE).
