package co.id.bankbsi.dashboardumroh.dashboardumroh.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@ConfigurationProperties("jwt")
data class JwtProperties(
    val key:String,
    val accessTokenExpiration:Long,
    val refreshTokenExpiration:Long
)
