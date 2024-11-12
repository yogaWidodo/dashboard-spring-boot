package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
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
    var password: String,
)