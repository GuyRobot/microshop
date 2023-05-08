package com.guysrobot.orderservice.dto

data class InventoryResponse(
    val skuCode: String,
    val isInStock: Boolean
)
