package user.config

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import user.v1.repository.UserRepository

@Service
class CustomUserDetailService(
    val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val userEntity = userRepository.findByEmail(username) ?: throw UsernameNotFoundException("사용자를 찾을 수 없습니다.")
        return CustomUser(userEntity)
    }

}