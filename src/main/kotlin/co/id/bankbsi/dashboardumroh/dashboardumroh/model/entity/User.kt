package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "table_user")
data class User(
    @Column(name = "id_user")
    @Id
    val idUser: String,

    @Column(name = "user_ldap")
    val userLdap: String,

    @Column(name = "nama")
    val nama: String,

    @Column(name = "unit")
    val unit: String,

    @Column(name = "id_role")
    val idRole: String,

    @Column(name = "status")
    val status: String,

    @Column(name = "created_at")
    val createdAt: Date,

    @Column(name = "last_login")
    val lastLogin: Date
)