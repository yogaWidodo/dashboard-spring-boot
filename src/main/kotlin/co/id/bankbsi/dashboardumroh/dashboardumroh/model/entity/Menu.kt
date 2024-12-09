package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "table_menu")
data class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    val idMenu: Int = 0,

    @Column(name = "nama_menu")
    var namaMenu:String,

    @Column(name = "status")
    var status: String,


)
