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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "table_role_menu",
        joinColumns = [JoinColumn(name = "id_role")],
        inverseJoinColumns = [JoinColumn(name = "id_menu")]
    )
    var menus:List<Menu> = listOf()

)
