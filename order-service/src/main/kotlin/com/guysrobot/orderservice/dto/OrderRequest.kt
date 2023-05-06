package com.guysrobot.orderservice.dto

data class OrderRequest(
    val items: List<OrderLineItemDto> = listOf()
)
