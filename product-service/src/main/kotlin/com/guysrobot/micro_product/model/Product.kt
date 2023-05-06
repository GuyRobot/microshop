package com.guysrobot.micro_product.model

import com.guysrobot.micro_product.dto.ProductResponse
import org.springframework.data.annotation.Id
import java.math.BigDecimal

data class Product(
    val name: String?, val description: String?, val price: BigDecimal,
    @Id val id: String? = null
) {
    fun toResponse(): ProductResponse {
        return ProductResponse(name, description, price, id)
    }
}