# ADR-001: Uso de Spring Boot

## Contexto
El proyecto es un servicio web REST para la gestión de ofertas. Se requiere un framework que permita desarrollar rápidamente, con integración de componentes como Spring Data, Web, y facilidades para testing.

## Problema
Seleccionar el framework adecuado para desarrollar una aplicación robusta y escalable que facilite la inyección de dependencias, gestión de la configuración y soporte para pruebas.

## Decisión
Se decidió utilizar **Spring Boot** como framework principal. Entre las razones:
- **Rapidez de desarrollo:** Configuración mínima para levantar un servicio REST.
- **Integración nativa:** Soporte para Spring Data JPA, Web, y herramientas de testing.
- **Ecosistema:** Gran comunidad, abundante documentación y plugins de Maven.
- **Producción Ready:** Características listas para producción (monitorización, métricas, etc.).

## Consecuencias
- Se simplifica el arranque y la configuración de la aplicación.
- Se adhiere a estándares y patrones ampliamente utilizados.
- Se depende del ecosistema Spring, lo cual facilita futuras integraciones.
