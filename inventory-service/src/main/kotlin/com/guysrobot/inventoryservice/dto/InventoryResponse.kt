package com.guysrobot.inventoryservice.dto

data class InventoryResponse(
    val skuCode: String,
    val isInStock: Boolean
)
