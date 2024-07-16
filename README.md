<p align="center">
  <img width="170" src="/img/logo-alura.png">
</p>
<div align="center">
<!-- Download -->
<a href="https://github.com/bastndev/Markdown-24/releases/tag/v2.0.1">
   <img src="https://img.shields.io/badge/App-DOWNLOAD-green?logo=microsoftstore&logoColor=green">
   <img src="https://img.shields.io/badge/dart-v1.0-red">
   <img src="https://img.shields.io/badge/java-v17-purple">
   
  </a>
</div>

# Foro Hub - Alura Latam Challenge

Foro Hub es un proyecto de aplicación web desarrollado como parte del desafío de Alura Latam. Este proyecto tiene como objetivo crear un foro donde los usuarios puedan registrarse, iniciar sesión, crear y responder comentarios.

## Tecnologías y Dependencias

El proyecto utiliza varias tecnologías y dependencias, cada una de las cuales se puede instalar y configurar de la siguiente manera:

### Herramientas y Configuración

1. **Java 17**
   - Descargar desde: [Java SE Development Kit 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
   - Instalar siguiendo las instrucciones de la página oficial.

2. **Spring Boot 3.3.1**
   - Añadir la dependencia en `pom.xml`:
    ```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.1</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    ```

3. **PostgreSQL 42.2.23**
   - Descargar desde: [PostgreSQL Downloads](https://www.postgresql.org/download/)
   - Instalar siguiendo las instrucciones de la página oficial.
   - Añadir la dependencia en `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.2.23</version>
    </dependency>
    ```

4. **Hibernate 5.6.0.Final**
   - Añadir la dependencia en `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.6.0.Final</version>
    </dependency>
    ```

5. **Lombok**
   - Añadir la dependencia en `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.24</version>
        <scope>provided</scope>
    </dependency>
    ```

6. **Spring Security**
   - Añadir la dependencia en `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    ```

7. **Spring Data JPA**
   - Añadir la dependencia en `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```

8. **Validation**
   - Añadir la dependencia en `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    ```

9. **Spring Boot DevTools**
   - Añadir la dependencia en `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
    ```

10. **Spring Boot Actuator**
   - Añadir la dependencia en `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    ```

11. **HikariCP**
   - Añadir la dependencia en `pom.xml`:
    ```xml
    <dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
        <version>5.0.1</version>
    </dependency>
    ```

12. **Liquibase**
   - Añadir la dependencia en `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.liquibase</groupId>
        <artifactId>liquibase-core</artifactId>
        <version>4.13.0</version>
    </dependency>
    ```

### Configuración de la Base de Datos

1. **application.properties**
   Configurar las propiedades de la base de datos en `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=url=jdbc:postgresql://${AD_HOST}/${AD_NAME}
    spring.datasource.username=t${AD_USER}
    spring.datasource.password=${AD_PASSWORD}
    spring.datasource.driver-class-name=org.postgresql.Driver

    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=update

    spring.liquibase.enabled=true
    spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml
    ```

### Estructura del Proyecto

- **Usuario:** Gestión de usuarios, incluyendo registro y autenticación.
- **Comentario:** Gestión de comentarios dentro del foro.
- **Controladores REST:** Definición de endpoints RESTful para la gestión de usuarios y comentarios.
- **Seguridad:** Configuración de seguridad con Spring Security.

### Ejecución del Proyecto

Para ejecutar el proyecto, sigue estos pasos:

1. Clonar el repositorio:
    ```bash
    git clone https://github.com/Angel66694/foro_hub.git
    cd foro_hub
    ```

2. Construir y ejecutar la aplicación:
    ```bash
    ./mvnw spring-boot:run
    ```

### Proceso de Elaboración

El desarrollo de Foro Hub fue un desafío interesante y enriquecedor. Sin embargo, uno de los principales retos fue manejar el tiempo debido a compromisos laborales. La configuración de la base de datos y la integración de varias dependencias como Liquibase y Spring Security también presentaron algunos desafíos técnicos. A pesar de estas dificultades, el proyecto fue completado exitosamente, proporcionando una valiosa experiencia en el desarrollo de aplicaciones web con Spring Boot.

¡Gracias por revisar este proyecto! No dudes en contribuir o hacer preguntas si tienes alguna duda.

© 2024 Angel6694 strikemigue94@gmal.com# alura_foro_hub
