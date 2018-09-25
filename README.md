When database is set up (below "Setting up MySQL database")
```
Open application.properties file at src/main/resources/
You'll find the lines:

1) spring.jpa.hibernate.ddl-auto=update
# spring.jpa.hibernate.ddl-auto=
# none        - This is the default for MySQL, no change to the database structure.
# update      - Hibernate changes the database according to the given Entity structures.
# create      - Creates the database every time, but don’t drop it when close.
# create-drop - Creates the database then drops it when the SessionFactory closes.

2) spring.datasource.url=jdbc:mysql://localhost:3306/veeb2018
# replace "veeb2018" with the database name you want to use in you app

3) spring.datasource.username=root
# replace "root" with the user that accesses the database

4) spring.datasource.password=AKSAdmin123.
# replace "AKSAdmin123." with the user password
```

Setting up MySQL database:
```
1. Download MySQL

2. Run installation

3. Open command line and move to MySQL folder
windows: cd C:\Program Files\MySQL\MySQL Workbench 8.0 CE (default)
```

The following tasks can all be done from MySQL Workbench, but
if you want to know some of the MySQL prompt language then visit 
[WikiHow](https://www.wikihow.com/Create-a-Database-in-MySQL).

Some of the basic commands:

```
4. Log in with the user made during installation.
(For example for me {username: root, password: AKSAdmin123.})
type "mysql -u root -p" and it will ask you for the password.
After that you should be in MySQL prompt and see "MySQL >" tag.

5. Creating the database
in MySQL prompt
type "create database database_name;"

6. Using the database
in MySQL prompt
type "use database_name;"

7. Creating tables
CREATE TABLE pet (name VARCHAR(20), owner VARCHAR(20), age INT,
    -> species VARCHAR(20), sex CHAR(1), birth DATE, death DATE);
    
8. Inserting data into table
insert into pet (col_name, col_owner) values("name", "owner")
```