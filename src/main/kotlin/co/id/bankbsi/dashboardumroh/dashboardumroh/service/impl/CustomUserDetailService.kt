package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl

import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.User

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
) : UserDetailsService {
    override fun loadUserByUsername(userLdap: String): UserDetails =
        userRepository.findByUserLdap(userLdap)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User not found")


    private fun ApplicationUser.mapToUserDetails(): UserDetails {
        val roles = roleRepository.findAll().map { it.namaRole }
        return User.builder()
            .username(this.userLdap)
            .password(this.nama)
            .roles(*roles.toTypedArray())
            .build()
    }
}