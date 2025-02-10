package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.MenuResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.RoleResponse
import jakarta.persistence.*

@Entity
@Table(name = "table_role", schema = "UMROH123")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    val idRole: Int = 0,

    @Column(name = "nama_role")
    var namaRole: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "table_role_menus",
        schema = "UMROH123",
        joinColumns = [JoinColumn(name = "id_role")],
        inverseJoinColumns = [JoinColumn(name = "id_menu")]
    )
    var menus: List<Menu> = listOf()

)

fun Role.mapToRoleResponse(): RoleResponse {
    return RoleResponse(
        idRole = this.idRole,
        namaRole = this.namaRole,
        menus = this.menus.map {
            MenuResponse(
                idMenu = it.idMenu,
                namaMenu = it.namaMenu,
                status = it.status
            )
        }
    )
}
