package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.UserResponse
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "table_user", schema = "UMROH123")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    val idUser: Int = 0,

    @Column(name = "user_ldap", unique = true)
    val userLdap: String,

    @Column(name = "nama")
    var nama: String,

    @Column(name = "unit")
    var unit: String,

    @JoinColumn(name = "id_role")
    @ManyToOne
    val idRole: Role,

    @Column(name = "status")
    var status: String,

    @Column(name = "created_at")
    val createdAt: Date,

    @Column(name = "last_login")
    var lastLogin: Date,

//    @Column(name = "password")
//    var passwordLdap: String,
)

fun User.mapToUserResponse(): UserResponse {
    return UserResponse(
        idUser = this.idUser,
        userLdap = this.userLdap,
        nama = this.nama,
        unit = this.unit,
        role = this.idRole,
        status = this.status,
        createdAt = this.createdAt,
        lastLogin = this.lastLogin,
    )
}

