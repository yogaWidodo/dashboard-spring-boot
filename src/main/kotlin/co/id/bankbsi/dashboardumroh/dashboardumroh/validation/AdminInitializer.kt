//package co.id.bankbsi.dashboardumroh.dashboardumroh.validation
//
//import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Menu
//import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Role
//import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.User
//import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.MenuRepository
//import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.RoleRepository
//import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.UserRepository
//import org.springframework.boot.CommandLineRunner
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.crypto.password.PasswordEncoder
//import java.util.*
//
//@Configuration
//class AdminInitializer {
//
//    @Bean
//    fun init(
//        userRepository: UserRepository,
//        roleRepository: RoleRepository,
//        menuRepository: MenuRepository,
//        passwordEncoder: PasswordEncoder
//    ): CommandLineRunner {
//        return CommandLineRunner {
//            // Create Admin Role
//            val adminRole = Role(namaRole = "ADMIN")
//            roleRepository.save(adminRole)
//
//            // Create Admin Menu
//            val adminMenu = Menu(namaMenu = "all-menu", status = "Active")
//            menuRepository.save(adminMenu)
//
//            // Assign Menu to Role
//            adminRole.menus.plus(adminMenu)
//            roleRepository.save(adminRole)
//
//            // Create Admin User
//            val adminUser = User(
//                nama = "admin",
//                unit = "IT",
//                idRole = adminRole,
//                status = "Active",
//                createdAt = Date(),
//                lastLogin = Date(),
//                userLdap = "admin",
//                passwordLdap = passwordEncoder.encode("123")
//            )
//            userRepository.save(adminUser)
//        }
//    }
//}