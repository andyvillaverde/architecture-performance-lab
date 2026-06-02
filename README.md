# Architecture Performance Lab

## Descripción General

Este repositorio contiene un conjunto de experimentos de arquitectura y rendimiento diseñados para comparar diferentes modelos de ejecución y concurrencia en escenarios de integración basados en microservicios.

El objetivo es evaluar el comportamiento de arquitecturas bloqueantes y no bloqueantes al interactuar con servicios externos de alta latencia, analizando su impacto en la escalabilidad, consumo de recursos, rendimiento y tiempos de respuesta.

Los experimentos fueron ejecutados en un entorno distribuido utilizando máquinas independientes para la generación de carga, el procesamiento de solicitudes y la simulación de servicios externos.

---

## Objetivos

* Comparar modelos de ejecución bloqueantes y reactivos.
* Analizar la utilización de recursos bajo cargas concurrentes.
* Medir el impacto de la latencia de servicios externos sobre la escalabilidad de las aplicaciones.
* Evaluar el comportamiento de distintos enfoques arquitectónicos en escenarios reales de integración.

---

## Proyectos

### external-service

Simula una API externa con alta latencia.

Características:

* API REST desarrollada con Spring Boot.
* Simulación de tiempos de respuesta mediante retardos controlados.
* Registro de solicitudes y monitoreo de hilos de ejecución.
* Utilizado como dependencia común para todos los escenarios evaluados.

### mvc

Implementación tradicional basada en procesamiento bloqueante.

Características:

* Spring MVC.
* Modelo de ejecución Thread-per-Request.
* Comunicación síncrona con servicios externos.

### reactive-webclient

Implementación basada en programación reactiva.

Características:

* Spring WebFlux.
* Arquitectura basada en Event Loops.
* Cliente HTTP no bloqueante mediante WebClient.
* Procesamiento reactivo de solicitudes.

---

## Entorno de Pruebas

### Máquina 1

Responsabilidades:

* Simulación del servicio externo.
* Generación de carga mediante k6.

Componentes:

* external-service
* k6

### Máquina 2

Responsabilidades:

* Sistema bajo evaluación.

Componentes:

* mvc
* reactive-webclient

---

## Arquitectura

Generador de Carga (k6)
|
v
+-------------------+
| MVC / WebFlux API |
+-------------------+
|
v
+-------------------+
| Servicio Externo  |
+-------------------+

---

## Métricas Recopiladas

Durante cada ejecución se recopilaron las siguientes métricas:

* Solicitudes concurrentes.
* Solicitudes activas.
* Utilización de hilos.
* Tiempos de respuesta.
* Throughput.
* Consumo de CPU.
* Consumo de memoria.

---

## Metodología de Evaluación

1. Desplegar el servicio externo.
2. Ejecutar la implementación MVC bajo carga.
3. Capturar métricas de rendimiento y consumo de recursos.
4. Ejecutar la implementación reactiva bajo las mismas condiciones.
5. Comparar los resultados y analizar los compromisos arquitectónicos de cada enfoque.

---

## Tecnologías Utilizadas

* Java
* Spring Boot
* Spring MVC
* Spring WebFlux
* Maven
* k6
* APIs REST

---

## Descargo de Responsabilidad

El objetivo de este repositorio no es promover una tecnología o framework en particular, sino proporcionar experimentos prácticos y reproducibles que permitan comprender las ventajas, limitaciones y compromisos asociados a distintos modelos de ejecución y enfoques arquitectónicos.
