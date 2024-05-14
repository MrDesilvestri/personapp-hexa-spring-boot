**INSTALAR MARIADB EN DOCKER:**

1. busque la imagen de mariaDB en la biblioteca de docker usando el buscador de la aplicacion docker desktop:
2. Ejecutar el siguiente comando en el CMD:
    * docker run --name mariadbc -p3307:3306 -e MARIADB_ROOT_PASSWORD=1234 -e MYSQL_USER=persona_db -e MYSQL_PASSWORD=persona_db -e MYSQL_DATABASE=lab -d mariadb:latest
3. Actualizar el linux de la imagen:
  * ejecute el siguente comando en el cmd para poder entrar al conenedor:
    * docker exec -it mariadbc bash
  * Ejecutar el comando para actualizar el linux
    * apt update
4. instalar mysql client dentro del contenedor de maria.
  * apt install mysql-client
5. buscar la imagen de phpmyadmin que se encuentra en la biblioteca de docker
6. ejecutar el siguiente comando para crear el contenedor de phpmyadmin
  * docker run --name phplab -d -p8080:80 --link mariadbc:db -e PMA_PORT=3307 phpmyadmin
7. abrir en el navegador de preferencia el siguiente link:
* localhost:8080
