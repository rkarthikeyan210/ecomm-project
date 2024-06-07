#Setup mysql via docker container

[//]: # (docker run --name ecomm-db -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql)
[//]: # (docker exec -it ecomm-db mysql -uroot -proot)

#Command to setup new database, user

[//]: # (CREATE  DATABASE userservice;)
[//]: # (CREATE USER "userservice"@"%" IDENTIFIED BY "userservice";)
[//]: # (GRANT ALL PRIVILEGES ON userservice.* TO  "userservice"@"%";)