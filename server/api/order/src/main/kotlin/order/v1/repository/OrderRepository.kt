package order.v1.repository

import order.v1.model.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderEntity, Long> {
    fun findAllByUserId(userId: String): List<OrderEntity>
}