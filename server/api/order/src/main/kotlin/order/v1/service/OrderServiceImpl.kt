package order.v1.service

import order.v1.model.dto.OrderRequest
import order.v1.model.dto.OrderResponse
import order.v1.model.entity.OrderEntity
import order.v1.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class OrderServiceImpl(
    val orderRepository: OrderRepository
) : OrderService {
    @Transactional
    override fun addOrder(userId: String, request: OrderRequest): OrderResponse {
        val orderEntity = orderRepository.save(
            request.let {
                OrderEntity(
                    it.productId,
                    it.qty,
                    it.unitPrice,
                    it.totalPrice,
                    userId,
                    UUID.randomUUID().toString()
                )
            }
        )
        return OrderResponse(orderEntity)
    }

    override fun findOrderByUserId(userId: String): List<OrderResponse> {
        val orderEntity = orderRepository.findAllByUserId(userId)
        return orderEntity.map(::OrderResponse)
    }
}