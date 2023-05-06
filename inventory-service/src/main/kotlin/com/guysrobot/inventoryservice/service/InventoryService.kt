package com.guysrobot.inventoryservice.service

import com.guysrobot.inventoryservice.repository.InventoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InventoryService(
    private val inventoryRepository: InventoryRepository
) {

    @Transactional(readOnly = true)
    fun checkIsInStock(skuCode: String): Boolean {
        return inventoryRepository.findBySkuCode(skuCode).isPresent
    }
}