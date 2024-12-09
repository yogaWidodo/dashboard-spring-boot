package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "table_role")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    val idRole:Int = 0,

    @Column(name = "nama_role")
    var namaRole:String,

    @OneToMany(targetEntity = Menu::class)
    var menus:MutableList<Menu> = mutableListOf()

)
