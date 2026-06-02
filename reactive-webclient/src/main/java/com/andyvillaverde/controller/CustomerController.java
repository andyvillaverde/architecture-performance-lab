package com.andyvillaverde.controller;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andyvillaverde.DTO.CustomerResponse;
import com.andyvillaverde.service.ExternalService;


import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")

public class CustomerController {

    private final ExternalService externalService;
    
    private final AtomicInteger requestCounter =
            new AtomicInteger();

    private final AtomicInteger activeRequests =
            new AtomicInteger();

    
    public CustomerController(ExternalService externalService) {
        this.externalService = externalService;
    }
    
    @GetMapping
    public Mono<CustomerResponse> getCustomer() {

        int requestId =
                requestCounter.incrementAndGet();

        int active =
                activeRequests.incrementAndGet();
    	
        System.out.println(
                "[CTRL-" + requestId + "] START"
              + "[ACTIVE=" + active + "]"
                + LocalDateTime.now()
                + " THREAD="
                + Thread.currentThread().getName());
    	
        return externalService.getCustomer()
        		.doFinally(signal -> {
		            int remaining =
		                    activeRequests.decrementAndGet();
		            System.out.println(
		                    "[CTRL-" + requestId + "] END  "
		                    + "[ACTIVE=" + remaining + "]"
		                    + LocalDateTime.now()
		                    + " THREAD="
		                    + Thread.currentThread().getName());
		        });
    }
}