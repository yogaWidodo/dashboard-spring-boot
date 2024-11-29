package co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh

import java.sql.Date

data class UmrohMapAccResponse(

    val idTravel: String,

    val kdTravel: String,

    val noRekening: String,

    val status: Int,

    val createDate: Date,

    val lastUpdate: Date,

    val createBy:String,

    val idMapAcc: Int,

    val namaTravel: String,

    val nominalFee :Int,

    val alamat: String,

    val kota: String,

    val email: String,

    val website: String,

    val logoTravel: String,

    val backround: String,

    val telp: String
)
