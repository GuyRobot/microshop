package com.guysrobot.inventoryservice.repository

import com.guysrobot.inventoryservice.model.Inventory
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface InventoryRepository : CrudRepository<Inventory, Long> {
    fun findBySkuCode(skuCode: String): Optional<Inventory>
}