package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.User
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.User
@Service
class CustomUserDetailService(
    private val userRepository:UserRepository
) :UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByUserLdap(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User not found")

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        org.springframework.security.core.userdetails.User.builder()
            .username(this.userLdap)
            .password(this.password)
            .roles(this.idRole.namaRole)
            .build()

}
