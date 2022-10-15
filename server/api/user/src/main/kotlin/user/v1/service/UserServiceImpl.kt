package user.v1.service

import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import user.v1.model.dto.OrderResponse
import user.v1.model.dto.RegisterUserRequest
import user.v1.model.dto.UserDto
import user.v1.model.dto.UserResponse
import user.v1.repository.UserRepository

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    val userRepository: UserRepository,
    val encoder: PasswordEncoder
    //val orderServiceClient: OrderServiceClient
) : UserService {
    private val logger = LoggerFactory.getLogger(UserService::class.java)

    @Transactional
    override fun addUser(request: RegisterUserRequest): UserResponse {
        val userEntity = request.toEntity().apply {
            this.password = encoder.encode(password)
        }
        val saveEntity = userRepository.save(userEntity)
        return UserResponse(saveEntity)
    }

    override fun findAllUser(): List<UserResponse> {
        return userRepository.findAll().map(::UserResponse)
    }

    override fun findByUserId(userId: String): UserResponse {
        val userEntity = userRepository.findByUserId(userId) ?: throw UsernameNotFoundException("사용자를 찾을 수 없습니다.")
        val userDto = ModelMapper().map(userEntity, UserDto::class.java)
        //userDto.orders = mutableListOf()

        return UserResponse(userDto.email, userDto.name, userDto.userId)
    }

}