# VizitPasswordGenerator
Генератор паролей (4 цифры) для домофонов Vizit

Генерирует неповторяющиеся коды, состоящие строго из четырех цифр.
Вообще-то, MainActivity работает с интерфейсом Generator, а генератор паролей из четырех цифр (FourDigitGenerator) это одна из его реализаций. Но для первой версии захардкодил в MainActivity создание именно FourDigitGenerator и никаких возможностей выбрать другой вариант из ГУИ нет.

Класс PasswordGenerator\FixedDigitGenerator - генерирование цифровых паролей строго фиксированной длины.
PasswordGenerator\FourDigitGenerator и PasswordGenerator\OneDigitGenerator - его потомки.
Последний создан только для целей тестирования.
