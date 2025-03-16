# ADR-006: Dockerización de la Aplicación

## Contexto
Se busca facilitar el despliegue, testing y consistencia del entorno de la aplicación mediante el uso de contenedores.

## Problema
Garantizar que la aplicación se ejecute de manera consistente en diferentes entornos, minimizando problemas de configuración local.

## Decisión
Se decidió **dockerizar** la aplicación creando un `Dockerfile` y, opcionalmente, utilizando `docker-compose` para gestionar servicios complementarios. Los puntos clave son:
- **Dockerfile:** Se usa una imagen base con OpenJDK 17 para ejecutar el JAR.
- **Docker Compose:** Facilita la orquestación en caso de que se integren otros servicios (como bases de datos externas) en el futuro.

## Consecuencias
- Se mejora la portabilidad y consistencia del entorno de ejecución.
- Facilita la integración en pipelines CI/CD.
- Se requiere conocimiento básico de Docker para gestionar imágenes y contenedores.
