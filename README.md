# Architecture Performance Lab

## Overview

This repository contains a set of architecture and performance experiments designed to compare different execution and concurrency models in microservice-based integration scenarios.

The objective is to evaluate how blocking and non-blocking architectures behave when interacting with slow external services, measuring their impact on scalability, resource consumption, throughput and response times.

The experiments were executed in a distributed environment using separate machines for load generation, service consumption and external service simulation.

---

## Objectives

* Compare blocking and reactive execution models.
* Analyze resource utilization under concurrent workloads.
* Measure the impact of external service latency on application scalability.
* Evaluate how different architectural approaches behave in real-world integration scenarios.

---

## Projects

### external-service

Simulates a slow external API.

Characteristics:

* Spring Boot REST API
* Artificial latency using delayed responses
* Request counters and thread tracking
* Used as a dependency for all benchmark scenarios

### mvc

Traditional blocking implementation.

Characteristics:

* Spring MVC
* Thread-per-request execution model
* Synchronous communication with external services

### reactive-webclient

Reactive implementation.

Characteristics:

* Spring WebFlux
* Event Loop architecture
* Non-blocking HTTP client using WebClient
* Reactive request processing

---

## Test Environment

### Machine 1

Responsibilities:

* External service simulation
* Load generation using k6

Components:

* external-service
* k6

### Machine 2

Responsibilities:

* System under test

Components:

* mvc
* reactive-webclient

---

## Architecture

Load Generator (k6)
|
v
+-------------------+
| MVC / WebFlux API |
+-------------------+
|
v
+-------------------+
| External Service  |
+-------------------+

---

## Metrics Collected

The following metrics were collected during each test execution:

* Concurrent requests
* Active requests
* Thread utilization
* Response times
* Throughput
* CPU consumption
* Memory consumption

---

## Benchmark Methodology

1. Deploy the external service.
2. Execute the MVC implementation under load.
3. Capture performance metrics.
4. Execute the reactive implementation under identical conditions.
5. Compare results and analyze architectural trade-offs.

---

## Technologies

* Java
* Spring Boot
* Spring MVC
* Spring WebFlux
* Maven
* k6
* REST APIs

---

## Disclaimer

The goal of this repository is not to promote a specific framework, but to provide practical and reproducible experiments that help understand the trade-offs between different execution models and architectural approaches.
