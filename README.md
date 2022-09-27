# RESTful API la cual permite visualizar proyectos del Senado de Colombia
Este proyecto se construyo usando **Java 11** y las siguientes herramientas:
- [Spring Boot](https://spring.io/projects/spring-boot) como framework y servidor
- [Maven](https://maven.apache.org/) como herramienta de compilación
- [Hibernate](https://hibernate.org/) como ORM / JPA implementacion
- [MySQL](https://www.mysql.com/) como base de datos
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) como capa de comunicacion con DB
- [Flyway](https://flywaydb.org/) as db migration tool

# Application Structure

### Model

El modelo de dominio está organizado bajo el paquete **modelo** y consta de clases de entidad. Las entidades usan varias anotaciones que describen el
relaciones entre unos y otros. JPA utiliza todas estas anotaciones para asignar entidades a las tablas de la base de datos.


### DTO

DTO significa **Objeto de transferencia de datos** y lo utilizamos para desacoplar la capa del modelo del lado del cliente.
Hay muchos enfoques sobre este tema, pero en mi opinión, el más limpio es transferir solo los datos necesarios mediante DTO, en lugar de
poblando todo el modelo. Para proyectos pequeños (como este), es común que un DTO sea idéntico al modelo correspondiente. Los DTO
representan los datos entrantes y salientes que manejan nuestras aplicaciones. Para aplicaciones con mayor lógica de negocios, es posible
crear dos o más DTO independientes (por ejemplo, representación de datos de solicitud y respuesta).

### Repo

Los repo son interfaces que son responsables de la persistencia y recuperación de datos. La capa de repo es una abstracción que proporciona el
CRUD y mantiene oculta la información relacionada con los datos (por ejemplo, la implementación de una base de datos específica) de las otras capas. esta capa siempre deben persistir las entidades.

### Service

La capa de servicio depende de la capa de repositorio, encapsulando toda la implementación de la lógica empresarial. Está allí para aplicar reglas de negocio sobre los datos enviados hacia y desde la capa del repo. La capa de servicio no se preocupa por la implementación específica de la base de datos y proporciona un bajo acoplamiento. En un eventual cambio de debase de datos esta estragia hace muy sencillo el poder realizarlo. Esta capa debe siempre recibe y devuelve DTO.

### Controller

La capa de controlador depende de la capa de servicio y es responsable de las solicitudes entrantes y las respuestas salientes. Un controlador determina todos los puntos finales disponibles que el lado del cliente (u otra API) puede llamar. Esta capa no debe aplicar lógica en los datos de recepción o devolución..

# Inicio Rapido

## Prerequisitos

#### 1. Crear la DB MySQL

```
CREATE DATABASE warehouse;
```
Acontinuacion tendra el modelo de datos utilizado para este proyecto:
 -  ```
    CREATE DATABASE SENADO_IUD_DB;
    ```

#### 2. Modificar el nombre de usuario y la contraseña de MySQL

- Abra el archivo ```src/main/resources/application.properties```
- Cambie las propiedades ```spring.datasource.username``` y ```spring.datasource.password``` para que coincidan con su conexión MySQL

## Crear el proyecto

Cree la aplicación usando el siguiente comando **maven wrapper**:
```
./mvnw clean install
```

Este comando creará un archivo ejecutable ```.jar``` en el directorio ```out```.

## Correr el Proyecto

Después de empaquetar la aplicación en un archivo ejecutable ```.jar```, puede iniciar el servidor ejecutando el siguiente comando usando cualquier terminal en el directorio del proyecto:
```
java -jar out/senado-iudigital-0.0.1.jar
```
Alternativamente, puede iniciar el servidor sin empaquetar, ejecutando directamente desde su IDE:
```
Run
```
El servidor comenzará a ejecutarse en http://localhost:8080.

# Documentacion de la API

Cuando el servidor este corriendo, puede usar swagger para explorar los endpoints disponibles y probarlos. Encuéntralo en:
http://localhost:8080/wh/swagger-ui.html#/

# Licencia
Este proyecto esta bajo licencia MIT. ver archivo LICENSE
