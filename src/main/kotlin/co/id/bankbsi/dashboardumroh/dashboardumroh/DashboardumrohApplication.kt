package co.id.bankbsi.dashboardumroh.dashboardumroh


import co.id.bankbsi.dashboardumroh.dashboardumroh.security.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class DashboardumrohApplication

fun main(args: Array<String>) {
    runApplication<DashboardumrohApplication>(*args)

}

