package user.config

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import user.v1.model.entity.UserEntity

class CustomUser(
    val userEntity: UserEntity
) : User(
        userEntity.email,
        userEntity.password,
        true,
        true,
        true,
        true,
        listOf(SimpleGrantedAuthority("ROLE_USER"))
    )