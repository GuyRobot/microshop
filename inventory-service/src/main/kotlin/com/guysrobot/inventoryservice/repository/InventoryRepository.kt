package com.guysrobot.inventoryservice.repository

import com.guysrobot.inventoryservice.model.Inventory
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface InventoryRepository : JpaRepository<Inventory, Long> {
    fun findBySkuCode(skuCode: String): Optional<Inventory>

    fun findBySkuCodeIn(skuCodes: List<String>): List<Inventory>

}