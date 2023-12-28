# Практика по JDBC

1. Подготовка: запустить БД postgreSQL (см. docker-compose.yml)
2. Подключиться к БД через dbeaver

## Задача: проектирование схемы данных
таблицы User и Role - у одного пользователя может быть несколько ролей, каждая роль может присвоена нескольким пользователям. Как связать?

1. Создаем таблицы User и Role
2. Создаем связующую таблицу UserToRole

## JDBC 

- Соединение с БД
```java
try(Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "postgres")) 
```

- Создание классов сущностей User и Role
- Запрашиваем сведения о пользователях и ролях, создаем объекты User и Role
