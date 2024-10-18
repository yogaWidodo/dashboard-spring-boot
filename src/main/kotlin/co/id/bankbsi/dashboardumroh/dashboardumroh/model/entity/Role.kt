package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "table_role")
data class Role(
    @Id
    @Column(name = "id_role")
    val idRole:String,

    @Column(name = "nama_role")
    val namaRole:String,

    @Column(name = "id_menu")
    val idMenu:String,


)
