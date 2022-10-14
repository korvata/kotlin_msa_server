package user.v1.repository

import org.springframework.data.jpa.repository.JpaRepository
import user.v1.model.entity.UserEntity

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUserId(userId: String): UserEntity?
    fun findByEmail(username: String?): UserEntity?
}