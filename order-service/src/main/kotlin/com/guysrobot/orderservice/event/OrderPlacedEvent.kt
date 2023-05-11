package com.guysrobot.orderservice.event

data class OrderPlacedEvent(
    private val orderNumber: String?
)