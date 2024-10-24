package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "table_menu")
data class Menu(
    @Id
    @Column(name = "id_menu")
    val idMenu: String,

    @Column(name = "nama_menu")
    val namaMenu:String,

    @Column(name = "status")
    val status: String,


)
