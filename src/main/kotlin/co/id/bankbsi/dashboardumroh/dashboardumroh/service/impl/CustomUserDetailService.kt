package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl

import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.User

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(userLdap: String): UserDetails =
        userRepository.findByUserLdap(userLdap)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User not found")

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.userLdap)
            .password(this.nama)
            .roles(this.idRole.namaRole)
            .build()

}