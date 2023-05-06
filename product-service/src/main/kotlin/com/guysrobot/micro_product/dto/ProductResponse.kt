package com.guysrobot.micro_product.dto

import org.springframework.data.annotation.Id
import java.math.BigDecimal

data class ProductResponse(
    val name: String?, val description: String?, val price: BigDecimal,
    @Id val id: String? = null
)