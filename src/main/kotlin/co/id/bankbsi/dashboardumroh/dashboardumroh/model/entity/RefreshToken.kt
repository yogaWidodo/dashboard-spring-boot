package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "refresh_token")
data class RefreshToken(
    @Id
    val token:String,
    val expiredDate:Date,
    @OneToOne
    @JoinColumn(name = "id_user")
    val user:User
)
