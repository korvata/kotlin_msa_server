package order.v1.controller

import order.v1.model.dto.OrderDto
import order.v1.model.dto.OrderRequest
import order.v1.model.dto.OrderResponse
import order.v1.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order-service/v1")
class OrderController(
    val orderService: OrderService
) {
    @PostMapping("/orders/{userId}")
    fun createOrder(
        @PathVariable userId: String,
        @RequestBody request: OrderRequest
    ): ResponseEntity<OrderResponse> {
        val orderResponse = orderService.addOrder(userId, request)

        return ResponseEntity.ok(orderResponse)
    }

    @GetMapping("/orders/{userId}")
    fun getOrderByUserId(
        @PathVariable userId: String
    ): ResponseEntity<List<OrderResponse>> {
        val orderResponse: List<OrderResponse> = orderService.findOrderByUserId(userId)
        return ResponseEntity.ok(orderResponse)
    }
}