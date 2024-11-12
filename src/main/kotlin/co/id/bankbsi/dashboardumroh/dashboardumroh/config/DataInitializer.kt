package co.id.bankbsi.dashboardumroh.dashboardumroh.config

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Menu
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Role
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.User
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

@Component
class DataInitializer(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val role = Role(idRole = "R004", namaRole = "ADMIN", menus = MutableList(0) {
            Menu(idMenu = "M003", namaMenu = "login", status = "Active")
        })
        roleRepository.save(role)

        val user = User(
            idUser = "1",
            userLdap = "admin",
            nama = "Admin User",
            unit = "IT",
            idRole = role,
            status = "ACTIVE",
            createdAt = Date(),
            lastLogin = Date(),
            password = passwordEncoder.encode("password")
        )
        userRepository.save(user)
    }
}