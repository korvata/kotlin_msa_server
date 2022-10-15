package order.v1.service

import order.v1.model.dto.OrderRequest
import order.v1.model.dto.OrderResponse

interface OrderService {
    fun addOrder(userId: String, request: OrderRequest): OrderResponse
    fun findOrderByUserId(userId: String): List<OrderResponse>

}