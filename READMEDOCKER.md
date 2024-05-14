**INSTALAR MARIADB EN DOCKER:**

1. busque la imagen de mariaDB en la biblioteca de docker usando el buscador de la aplicacion docker desktop:
  * o ejecute el siguiente comando en el cmd:
     * docker pull mariadb:latest 
3. Ejecutar el siguiente comando en el CMD:
    * docker run --name mariadbc -p3307:3306 -e MARIADB_ROOT_PASSWORD=1234 -e MYSQL_USER=persona_db -e MYSQL_PASSWORD=persona_db -e MYSQL_DATABASE=persona_db -d mariadb:latest
4. Actualizar el linux de la imagen:
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
* http://localhost:8080
8. autenticarse en Phpmyadmin con las siguientes credenciales:
   * Usuario: persona_db
   * Contraseña: persona_db
9. pegar en la ventana SQL de la base de datos el siguiente codigo SQL:
```sql
-- Creación de la tabla profesion
CREATE TABLE profesion (
    id INT(6) PRIMARY KEY,
    nom VARCHAR(90),
    des TEXT
);

-- Creación de la tabla persona
CREATE TABLE persona (
    cc INT(15) PRIMARY KEY,
    nombre VARCHAR(45),
    apellido VARCHAR(45),
    genero ENUM('M','F'),
    edad INT(3)
);

-- Creación de la tabla estudios
CREATE TABLE estudios (
    id_prof INT(6),
    cc_per INT(15),
    fecha DATE,
    univer VARCHAR(50),
    PRIMARY KEY (id_prof, cc_per),
    FOREIGN KEY (id_prof) REFERENCES profesion(id),
    FOREIGN KEY (cc_per) REFERENCES persona(cc)
);

-- Creación de la tabla telefono
CREATE TABLE telefono (
    num VARCHAR(15),
    oper VARCHAR(45),
    duenio INT(15),
    PRIMARY KEY (num),
    FOREIGN KEY (duenio) REFERENCES persona(cc)
);
```

**INSTALAR MARIADB EN DOCKER:**
1. Realizar la busqueda o descarga de la imagen con el mismo procedimeinto realizado para MariaDB:
  * docker pull mongo:latest
2. Ejecutar el siguiente comando en el CMD:
  *  docker run --name mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=persona_db -e MONGO_INITDB_ROOT_PASSWORD=persona_db -d mongo:latest
3. Conectar al Contenedor de MongoDB:
   * docker exec -it mongodb bash
4. Autenticarse dentro del contenedor:
 * mongo -u persona_db -p persona_db --authenticationDatabase admin
5. Instalar mongo express:
   * docker run --name mongo-express -d -p 8081:8081 --link mongodb:mongo -e ME_CONFIG_MONGODB_ADMINUSERNAME=persona_db -e ME_CONFIG_MONGODB_ADMINPASSWORD=persona_db -e ME_CONFIG_MONGODB_SERVER=mongodb mongo-express
6. acceder con el siguiente link:
   * http://localhost:8081
