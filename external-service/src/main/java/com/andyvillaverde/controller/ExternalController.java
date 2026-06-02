package com.andyvillaverde.controller;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andyvillaverde.dto.CustomerResponse;

@RestController
@RequestMapping("/external")
public class ExternalController {

	private final AtomicInteger requestCounter = new AtomicInteger();
	private final AtomicInteger activeRequests = new AtomicInteger();
	
    @GetMapping("/customer")
    public CustomerResponse customer()
            throws Exception {

    	
        int requestId =
                requestCounter.incrementAndGet();
        int currentActive =
                activeRequests.incrementAndGet();
        
        String threadName =
                Thread.currentThread().getName();

        System.out.println(
                "[REQUEST=" + requestId + "] START "
                + " [ACTIVE=" + currentActive +"]" 
                + LocalDateTime.now()
                + " THREAD=" + threadName);

        Thread.sleep(3000);
        currentActive =
                activeRequests.decrementAndGet();
        
        System.out.println(
                "[REQUEST=" + requestId + "] END   "
                + " [ACTIVE=" + currentActive +"]" 
                + LocalDateTime.now()
                + " THREAD=" + threadName);

        return new CustomerResponse(
                "OK FROM EXTERNAL SERVICE");
    }
}