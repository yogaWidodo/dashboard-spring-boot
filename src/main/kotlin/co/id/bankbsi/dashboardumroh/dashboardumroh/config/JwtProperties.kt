package co.id.bankbsi.dashboardumroh.dashboardumroh.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val key:String,
    val accessTokenExpiration:Long,
    val refreshTokenExpiration:Long
)
