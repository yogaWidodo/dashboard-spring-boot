package co.id.bankbsi.dashboardumroh.dashboardumroh.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider
) {
    @Bean
    fun securityFilterChain(
        http:HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter
    ):DefaultSecurityFilterChain =
        http
            .cors {Customizer.withDefaults<CorsConfiguration>()}
            .csrf{ it.disable() }
            .authorizeHttpRequests{
                it
                    .requestMatchers(HttpMethod.POST,"/api/auth","/api/auth/refresh","/api/error")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","OFFICER","REPORTING","SPV" )
                    .anyRequest()
                    .authenticated()
            }
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()


    @Bean
    fun corsConfiguration(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.allowedOrigins = listOf("*")
        config.allowedMethods = listOf("GET","POST","PUT","DELETE","OPTIONS")
        config.allowedHeaders = listOf("*")
        config.allowCredentials = true

        val urlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource()
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",config)
        return urlBasedCorsConfigurationSource
    }

}
