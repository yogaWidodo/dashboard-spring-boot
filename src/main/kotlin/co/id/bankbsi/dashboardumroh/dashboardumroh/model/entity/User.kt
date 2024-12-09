package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

@Entity
@Table(name = "table_user")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    val idUser: Int = 0,

    @Column(name = "user_ldap")
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

    @Column(name = "password")
    var passwordLdap: String,
)
//) : UserDetails {
//    override fun getAuthorities(): Collection<GrantedAuthority> {
//        return listOf(SimpleGrantedAuthority("ROLE_${idRole.namaRole}"))
//    }
//
//    override fun getPassword(): String {
//        return passwordLdap
//    }
//
//    override fun getUsername(): String {
//        return userLdap
//    }
//
//
//    override fun isAccountNonExpired(): Boolean = true
//    override fun isAccountNonLocked(): Boolean = true
//    override fun isCredentialsNonExpired(): Boolean = true
//    override fun isEnabled(): Boolean = true

