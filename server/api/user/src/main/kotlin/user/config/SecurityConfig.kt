package user.config

import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import user.config.filter.AuthenticationFilter


@EnableWebSecurity
class SecurityConfig(
    val env: Environment,
    val customUserDetailService: CustomUserDetailService
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.authorizeHttpRequests()
            .antMatchers("/actuator/**")
            .permitAll()
        http.authorizeHttpRequests()
            .antMatchers("/**")
            .permitAll()
            .and()
            .addFilter(getAuthenticationFilter())

        http.headers().frameOptions().disable()
    }

    private fun getAuthenticationFilter(): AuthenticationFilter {
        val authenticationFilter = AuthenticationFilter(env, customUserDetailService)
        authenticationFilter.setAuthenticationManager(this.authenticationManager())
        return authenticationFilter
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}