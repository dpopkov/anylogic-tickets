# anylogic-tickets

Тестовое задание.

## Использование

1. Собрать исполняемый jar файл: `mvn package`
2. Результат сборки будет находиться в под-директории `target` под именем `anylogic-tickets-1.0-shaded.jar`
3. Запустить командой `java -jar anylogic-tickets-1.0-shaded.jar`
    * Обрабатываемый файл должен находиться в той же директории, либо путь к нему должен быть передан как параметр
    * Возможные дополнительные параметры:
        * имя входного файла (по умолчанию tickets.json в текущей директории)
        * код origin (точка вылета, по умолчанию VVO)
        * код destination (точка прилета, по умолчанию TLV)
