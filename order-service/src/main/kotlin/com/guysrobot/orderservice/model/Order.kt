package com.guysrobot.orderservice.model

import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val orderNumber: String?,
    @OneToMany(cascade = [CascadeType.ALL])
    val orderLineItems: List<OrderLineItem> = listOf()
)