package com.andyvillaverde.service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.andyvillaverde.DTO.CustomerResponse;

import reactor.core.publisher.Mono;

@Service
//@RequiredArgsConstructor
public class ExternalService {

    private final WebClient webClient;
    
    private final AtomicInteger activeCalls =
            new AtomicInteger();
    
    public ExternalService(WebClient webClient) {
        this.webClient = webClient;
    }
    
    public Mono<CustomerResponse> getCustomer() {

//        int current =
//                activeCalls.incrementAndGet();
//    	
//        System.out.println(
//                "OUTBOUND START"
//                + " ACTIVE=" + current
//                + LocalDateTime.now()
//                + " THREAD="
//                + Thread.currentThread().getName());
        
        return webClient.get()
                .uri("/external/customer")
                .retrieve()
                .bodyToMono(CustomerResponse.class)
//                .doFinally(signal -> {
//
//                    int remaining =
//                            activeCalls.decrementAndGet();
//
//                    System.out.println(
//                            "OUTBOUND END"
//                            + " ACTIVE=" + remaining
//                            + LocalDateTime.now()
//                            + " THREAD="
//                            + Thread.currentThread().getName());
//                })
                ;
    }
}