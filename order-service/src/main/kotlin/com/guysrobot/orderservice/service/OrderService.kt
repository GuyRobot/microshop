package com.guysrobot.orderservice.service

import com.guysrobot.orderservice.dto.InventoryResponse
import com.guysrobot.orderservice.dto.OrderRequest
import com.guysrobot.orderservice.model.Order
import com.guysrobot.orderservice.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import java.lang.IllegalArgumentException
import java.util.UUID

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val webClientBuilder: WebClient.Builder
) {
    @Transactional
    fun create(orderRequest: OrderRequest) {
        val order = Order(
            orderNumber = UUID.randomUUID().toString(),
            orderLineItems = orderRequest.items.map { it.toModel() }
        )

        val skuCodes = order.orderLineItems.map { it.skuCode }

        val result = webClientBuilder.build().get()
            .uri("http://inventory-service/api/inventories") {
                return@uri it.queryParam("skuCode", skuCodes).build()
            }
            .retrieve()
            .bodyToMono(Array<InventoryResponse>::class.java)
            .block()

        if (!result.isNullOrEmpty()
            && result.all { it.isInStock }
        ) {
            orderRepository.save(order)
        } else {
            throw IllegalArgumentException("Product is not in stock")
        }
    }
}