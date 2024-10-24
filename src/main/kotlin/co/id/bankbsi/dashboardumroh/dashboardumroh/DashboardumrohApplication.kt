package co.id.bankbsi.dashboardumroh.dashboardumroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Menu
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Role
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.RoleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DashboardumrohApplication

fun main(args: Array<String>) {
    runApplication<DashboardumrohApplication>(*args)

}

