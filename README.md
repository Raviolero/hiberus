# Prueba Técnica Hiberus

Este proyecto es una implementación de un servicio REST para la gestión de ofertas en un entorno de e-commerce. Se ha desarrollado aplicando buenas prácticas y principios de arquitectura, utilizando **Spring Boot** con **Java 17** y una **arquitectura hexagonal** (Ports & Adapters). Además, cuenta con documentación interactiva mediante **Swagger/OpenAPI**, pruebas unitarias e integración, y está dockerizado para facilitar su despliegue.

## Características Principales

- **Endpoints REST** para:
  - Crear, eliminar y consultar ofertas.
  - Obtener el "timetable" de ofertas (aplanando intervalos de precios) para un producto específico.
- **Arquitectura Hexagonal** que separa la lógica de negocio (dominio) de las dependencias externas (persistencia, APIs REST, etc.).
- Uso de **Java 17**, aprovechando sus nuevas características como los *records* para objetos de valor.
- **Documentación automática** con Swagger/OpenAPI, que incluye ejemplos precargados en el "Try it out" de los endpoints.
- Pruebas **unitarias** (JUnit 5 y Mockito) y **de integración** para garantizar la calidad y el correcto funcionamiento.
- **Dockerización** de la aplicación, lo que permite ejecutar la solución de forma consistente en cualquier entorno.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot** (incluye Spring Data JPA, Spring Web, etc.)
- **H2 Database** (modo in-memory para desarrollo y testing)
- **Swagger/OpenAPI** (documentación interactiva)
- **Maven** (gestión de dependencias y build)
- **Docker** y **Docker Compose** (para contenedorización)
- **PlantUML** (documentación de diagramas de secuencia y casos de uso)
- **JUnit 5** y **Mockito** (para pruebas)

## Estructura del Proyecto

```plaintext
prueba-tecnica-01/
├── docs/
│   ├── adr/
│   │   ├── ADR-001-uso-spring-boot.md
│   │   ├── ADR-002-arquitectura-hexagonal.md
│   │   ├── ADR-003-java17.md
│   │   ├── ADR-004-h2-database.md
│   │   ├── ADR-005-swagger-openapi.md
│   │   └── ADR-006-dockerization.md
│   ├── diagrams/
│   │   ├── sequence/
│   │   │   └── offer_creation_sequence.puml
│   │   └── usecase/
│   │       └── offer_usecase.puml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── hiberus/
│   │               └── hiring/
│   │                   ├── adapter/
│   │                   │   ├── persistence/
│   │                   │   │   ├── OfferJpaRepository.java
│   │                   │   │   └── OfferRepositoryAdapter.java
│   │                   │   └── rest/
│   │                   │       └── OfferController.java
│   │                   ├── application/
│   │                   │   ├── port/
│   │                   │   │   └── OfferRepositoryPort.java
│   │                   │   └── service/
│   │                   │       ├── OfferService.java
│   │                   │       └── OfferServiceImpl.java
│   │                   └── domain/
│   │                       └── model/
│   │                           ├── Offer.java
│   │                           └── OfferByPartnumber.java
│   └── test/
│       └── java/
│           └── com/
│               └── hiberus/
│                   └── hiring/
│                       ├── adapter/
│                       │   └── rest/
│                       │       └── OfferControllerIntegrationTest.java
│                       └── application/
│                           └── service/
│                               └── OfferServiceTest.java
├── pom.xml
├── Dockerfile
└── docker-compose.yml
```

## Instalación y Ejecución

### Requisitos Previos

- **Java 17** instalado y configurado (JAVA_HOME).
- **Maven** instalado y en el PATH.
- **Docker** y opcionalmente **Docker Compose**.

### Ejecución Local

1. **Compilar y empaquetar la aplicación:**

   ```bash
   mvn clean package
Esto generará el JAR en la carpeta `target/`.

2. **Ejecutar la aplicación:**

   ```bash
   java -jar target/prueba-tecnica-0.0.1-SNAPSHOT.jar
2. **Acceder a Swagger:**
Una vez iniciada la aplicación, la documentación interactiva estará disponible en:
- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Ejecución con Docker

1. **Construir la imagen Docker:**

   ```bash
   docker build --no-cache -t hiberus-app:latest .
2. **Iniciar el contenedor:**

        ```bash
        docker run -d -p 8080:8080 --name hiberus-app hiberus-app:latest 
    Si usas Docker Compose, simplemente ejecuta:

        ```bash
        docker-compose up --build 
3. **Verificar que el contenedor esté corriendo:**

    ```bash
    docker ps
4. **Acceder a Swagger:**
    
    Como en la ejecución local, la documentación se encuentra en:
    - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Ejecución de Pruebas

Para ejecutar las pruebas unitarias y de integración, utiliza el siguiente comando:

```bash
mvn test
```

## Documentación Adicional

- **Diagramas PlantUML:**  
  Los diagramas de secuencia y casos de uso se encuentran en `docs/diagrams/`. Puedes visualizarlos usando un servidor de PlantUML o mediante la herramienta local:
  
  ```bash
  java -jar plantuml.jar docs/diagrams/sequence/offer_creation_sequence.puml
  java -jar plantuml.jar docs/diagrams/usecase/offer_usecase.puml

## ADR (Architectural Decision Records):

Todas las decisiones arquitectónicas relevantes se documentan en la carpeta `docs/adr/`. Revisa estos documentos para entender las decisiones clave que guían el desarrollo del proyecto.

## Servidor de PlantUML en Docker

Si deseas correr el servidor de PlantUML en un contenedor Docker (de forma independiente del Dockerfile del proyecto), sigue estos pasos:

1. **Descargar la imagen oficial de PlantUML:**

   ```bash
   docker pull plantuml/plantuml-server:jetty

Ejecutar el contenedor de PlantUML:

```bash
docker run -d -p 8081:8080 --name plantuml-server plantuml/plantuml-server:jetty
```

Ahora podrás acceder al servidor en [http://localhost:8081](http://localhost:8081).
