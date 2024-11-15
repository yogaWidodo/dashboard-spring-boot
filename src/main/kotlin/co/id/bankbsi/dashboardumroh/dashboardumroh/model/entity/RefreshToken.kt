package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.*
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

@Entity
@Table(name = "refresh_token")
data class RefreshToken(
    @Id
    val token:String,
    @OneToOne
    @JoinColumn(name = "id_user")
    val user: User
)
