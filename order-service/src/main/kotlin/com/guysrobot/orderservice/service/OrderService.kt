package com.guysrobot.orderservice.service

import com.guysrobot.orderservice.dto.OrderRequest
import com.guysrobot.orderservice.model.Order
import com.guysrobot.orderservice.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    @Transactional
    fun create(orderRequest: OrderRequest) {
        val order = Order(
            orderNumber = UUID.randomUUID().toString(),
            orderLineItems = orderRequest.items.map { it.toModel() }
        )

        orderRepository.save(order)
    }
}