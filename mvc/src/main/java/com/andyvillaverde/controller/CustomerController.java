package com.andyvillaverde.controller;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class CustomerController {

    private final RestClient restClient;

 // Contadores atómicos seguros para entornos multihilo
    private final AtomicInteger requestCounter = new AtomicInteger(0);
    private final AtomicInteger activeRequests = new AtomicInteger(0);
    
    // Inicializamos el cliente apuntando al servicio externo que tarda 3 segundos
    public CustomerController() {
        this.restClient = RestClient.builder()
                .baseUrl("http://192.168.18.9:8081") // Pon aquí la URL base del servicio externo
                .build();
    }

    @GetMapping("/customer")
    public Object getCustomers() {
    	
    	// 1. Incrementamos contadores al entrar
        int requestId = requestCounter.incrementAndGet();
        int active = activeRequests.incrementAndGet();
    	
        // Log de Inicio
        System.out.println(
                "[CTRL-" + requestId + "] START "
              + "[ACTIVE=" + active + "] "
                + LocalDateTime.now()
                + " THREAD="
                + Thread.currentThread().getName());
        
        // 2. Ejecución del bloqueo de 3 segundos con el servicio externo
        Object response = restClient.get()
                .uri("/external/customer")
                .retrieve()
                .body(Object.class); // Spring lo recibirá dinámicamente como un Map
    	
    	// 3. Decrementamos el contador de activos antes de salir
        int activeAfter = activeRequests.decrementAndGet();
        
        // Log de Fin (Opcional, pero súper útil para ver cuándo se liberan)
        System.out.println(
                "[CTRL-" + requestId + "] END "
              + "[ACTIVE=" + activeAfter + "] "
                + LocalDateTime.now()
                + " THREAD="
                + Thread.currentThread().getName());
        
        return response;
    	
    }
}
