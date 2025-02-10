package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.MenuResponse
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "table_menu", schema = "UMROH123")
data class  Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    val idMenu: Int = 0,

    @Column(name = "nama_menu")
    var namaMenu:String,

    @Column(name = "status")
    var status: String,

    @ManyToMany(mappedBy = "menus", fetch = FetchType.EAGER)
    @JsonIgnore
    var roles: List<Role> = listOf()
)
fun Menu.mapToMenuResponse(): MenuResponse {
    return MenuResponse(
        idMenu = idMenu,
        status = status,
        namaMenu = namaMenu,
    )
}