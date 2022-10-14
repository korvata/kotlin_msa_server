package user.v1.service

import user.v1.model.dto.RegisterUserRequest
import user.v1.model.dto.UserResponse

interface UserService {
    fun addUser(request: RegisterUserRequest): UserResponse
    fun findAllUser(): List<UserResponse>
    fun findByUserId(userId: String): UserResponse
}