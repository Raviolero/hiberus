# ADR-003: Uso de Java 17

## Contexto
El proyecto se desarrolla en un entorno moderno, y se desea aprovechar las nuevas características del lenguaje para mejorar la claridad y concisión del código.

## Problema
Seleccionar una versión de Java que permita el uso de funcionalidades modernas, como los *records* para objetos de valor, mejoras en el pattern matching, etc.

## Decisión
Se decidió utilizar **Java 17** debido a:
- Soporte a largo plazo (LTS) y estabilidad.
- Disponibilidad de *records* para definir de manera concisa objetos inmutables (por ejemplo, para `OfferByPartnumber`).
- Mejoras en el rendimiento y en la sintaxis que facilitan el desarrollo.

## Consecuencias
- Código más limpio y fácil de mantener.
- Dependencia en una versión LTS, lo que brinda seguridad y soporte a largo plazo.
- Es necesario asegurarse de que todas las herramientas y dependencias sean compatibles con Java 17.
