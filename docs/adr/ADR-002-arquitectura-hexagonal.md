# ADR-002: Arquitectura Hexagonal

## Contexto
El proyecto requiere mantener un código limpio y desacoplado que permita separar la lógica de negocio de las dependencias externas (persistencia, APIs, etc.).

## Problema
Diseñar la aplicación de manera que sea fácil de mantener, probar y evolucionar, aislando la lógica de negocio de detalles de implementación.

## Decisión
Se optó por utilizar la **Arquitectura Hexagonal** (también conocida como Ports and Adapters). Se estructuró el proyecto en los siguientes módulos:
- **Dominio:** Modelos y reglas de negocio.
- **Aplicación:** Servicios y lógica de negocio.
- **Adaptadores:** Implementaciones concretas de persistencia (JPA) y exposición REST.
- **Configuración:** Integración de herramientas externas como Swagger.

## Consecuencias
- Mayor desacoplamiento entre la lógica de negocio y la infraestructura.
- Facilita pruebas unitarias y de integración, permitiendo reemplazar fácilmente adaptadores.
- La estructura puede escalar para incorporar nuevos módulos o integraciones sin alterar la lógica central.
