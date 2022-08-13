IT_ONE Cup.Java

Задача для квалификационного раунда

https://cups.online/ru/workareas/itonecup_java/727/1347

Условия

Необходимо написать Spring-boot приложение, которое сможет управлять in-memory базой данных с помощью REST API. Описание API доступно в приложенном файле API.pdf

Ваше приложение должно уметь обрабатывать запросы, позволяющие последовательно сформировать “отчет” по базе данных. При этом сама база данных тоже будет наполняться через API.


Таким образом, на входе вашей системы будут запросы с исходными данными и со структурой желаемого отчета, а на выходе - агрегированные в соответствии со структурой отчета данные.


Проверка решения будет проводиться внутренними тестами-сценариями. То есть в общем случае, отдельный сценарий тестирования будет заключаться в следующего набора действий:

формирование структуры БД (добавление таблиц, полей)
наполнение БД данными (вставка записей в таблицы)
формирование структуры желаемого отчета (конструирование запросов к БД)
модификация данных (обновление и/или удаление записей в таблицах БД)
получение сформированного отчета

Строгое соблюдение указанной выше последовательности действий не гарантируется - ваше приложение должно корректно обрабатывать входящие запросы в любой последовательности. И в случаях некорректных или не имеющих смысла запросов приложение должно отвечать соответствующими кодами ошибок (см. описание api). 

