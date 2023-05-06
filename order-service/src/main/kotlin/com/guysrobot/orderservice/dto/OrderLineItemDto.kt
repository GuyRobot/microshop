package com.guysrobot.orderservice.dto

import com.guysrobot.orderservice.model.OrderLineItem
import java.math.BigDecimal

data class OrderLineItemDto(
    val id: Long = 0,
    val skuCode: String,
    val price: BigDecimal,
    val quantity: Int
) {
    fun toModel(): OrderLineItem {
        return OrderLineItem(id, skuCode, price, quantity)
    }
}
