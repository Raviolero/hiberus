# ADR-005: Uso de Swagger/OpenAPI para Documentación

## Contexto
La documentación clara de la API es fundamental para la comunicación con otros equipos y para facilitar el testing y la integración.

## Problema
Proveer una interfaz interactiva y documentación actualizada para la API REST.

## Decisión
Se decidió incorporar **Swagger/OpenAPI** mediante la dependencia `springdoc-openapi-ui`, lo que permite:
- Generar documentación interactiva accesible vía navegador.
- Mostrar ejemplos y facilitar el "try out" de los endpoints.
- Reducir el esfuerzo manual en mantener la documentación sincronizada con la implementación.

## Consecuencias
- Se añade una dependencia adicional, pero el beneficio de tener documentación interactiva es considerable.
- La documentación se genera automáticamente a partir de las anotaciones en el código.
