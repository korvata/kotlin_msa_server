package user.config.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.core.env.Environment
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import user.config.CustomUser
import user.config.CustomUserDetailService
import user.v1.model.dto.LoginRequest
import java.sql.Date
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    private val env: Environment,
    private val customUserDetailService: CustomUserDetailService
) : UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val loginRequest = ObjectMapper().registerModule(kotlinModule()).readValue(request!!.inputStream, LoginRequest::class.java)
        return authenticationManager.authenticate(
            loginRequest.let {
                UsernamePasswordAuthenticationToken(
                    it.email,
                    it.password
                )
            }
        )
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val userName = (authResult?.principal as? CustomUser)?.username ?: throw InternalAuthenticationServiceException("서비스 오류로 로그인에 실패하였습니다/")
        val userDto = customUserDetailService.getUserDetailsByEmail(userName)

        val expirationTime = env.getProperty("token.expiration_time")?.toLong()?: 86400
        val token = JWT.create()
            .withSubject(userDto.userId)
            .withExpiresAt(Date(System.currentTimeMillis() + expirationTime))
            .sign(Algorithm.HMAC512(env.getProperty("token.secret")))
        response!!.addHeader("token", token)
        response.addHeader("userId", userDto.userId)
    }
}