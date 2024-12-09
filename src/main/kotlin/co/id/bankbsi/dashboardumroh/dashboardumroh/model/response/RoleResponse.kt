package co.id.bankbsi.dashboardumroh.dashboardumroh.model.response



data class RoleResponse(
    val idRole:Int,

    val namaRole:String,

    val menus:List<MenuResponse>,
)