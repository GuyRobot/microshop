package com.guysrobot.orderservice.controller

import com.guysrobot.orderservice.dto.OrderRequest
import com.guysrobot.orderservice.service.OrderService
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    fun create(@RequestBody orderRequest: OrderRequest): CompletableFuture<String>? {
        return CompletableFuture.supplyAsync {
            orderService.create(orderRequest)
            "Order successfully"
        }
    }

    fun fallbackMethod(
        @RequestBody orderRequest: OrderRequest,
        runtimeException: RuntimeException
    ): CompletableFuture<String>? {
        return CompletableFuture.supplyAsync {
            "Oops! Something went wrong, please try again later"
        }
    }
}