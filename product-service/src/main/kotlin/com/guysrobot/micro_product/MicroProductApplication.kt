package com.guysrobot.micro_product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MicroProductApplication

fun main(args: Array<String>) {
	runApplication<MicroProductApplication>(*args)
}
