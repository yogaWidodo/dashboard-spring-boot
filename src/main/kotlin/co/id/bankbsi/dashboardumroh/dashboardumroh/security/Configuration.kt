package co.id.bankbsi.dashboardumroh.dashboardumroh.security

import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.UserRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.CustomUserDetailService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class Configuration {
    @Bean
    fun userDetailService(userRepository: UserRepository, roleRepository: RoleRepository): UserDetailsService =
        CustomUserDetailService(userRepository,roleRepository)



    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(userRepository: UserRepository, roleRepository: RoleRepository): AuthenticationProvider =
        DaoAuthenticationProvider()
            .also {
                it.setUserDetailsService(userDetailService(userRepository,roleRepository))
                it.setPasswordEncoder(encoder())
            }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager
}