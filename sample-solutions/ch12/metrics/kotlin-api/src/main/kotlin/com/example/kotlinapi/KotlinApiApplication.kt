package com.example.kotlinapi

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicInteger

@SpringBootApplication
class KotlinApiApplication {
	@Bean
	fun counter() = AtomicInteger(0)
}

fun main(args: Array<String>) {
	runApplication<KotlinApiApplication>(*args)
}

@RestController
class DemoController(private val counter: AtomicInteger, private val meterRegistry: MeterRegistry) {
	@GetMapping("/")
	fun hello(): String {
		val count = counter.incrementAndGet()
		meterRegistry.counter("api_requests_total", "path", "/").increment()
		return "Hello, World! Request count: $count"
	}
}