package user.config

import org.modelmapper.ModelMapper
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import user.v1.model.dto.UserDto
import user.v1.repository.UserRepository

//UserDetailsService를 상속 받은 CustomUserDetailService
//db에서 사용자ID(email)을 통해 userEntity를 가져온다
@Service
class CustomUserDetailService(
    val userRepository: UserRepository
) : UserDetailsService {
    private val mapper = ModelMapper()
    override fun loadUserByUsername(username: String?): UserDetails {
        val userEntity = userRepository.findByEmail(username) ?: throw UsernameNotFoundException("사용자를 찾을 수 없습니다.")
        return CustomUser(userEntity)
    }

    fun getUserDetailsByEmail(email: String): UserDto {
        val userEntity = userRepository.findByEmail(email) ?: throw UsernameNotFoundException("사용자를 찾을 수 없습니다.")
        return UserDto(
            userEntity.email,
            userEntity.name,
            userEntity.password,
            userEntity.userId
        )
    }

}