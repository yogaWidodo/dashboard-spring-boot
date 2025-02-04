package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "UMROH_MAP_ACC", schema = "UMROH123")
data class UmrohMapAcc(

    @Id
    @Column(name = "ID_MAP_ACC")
    var idMapAcc: Int,

    @Column(name = "ID_TRAVEL")
    val idTravel: String,

    @Column(name = "KD_TRAVEL")
    var kdTravel: String,

    @Column(name = "NO_REKENING")
    var noRekening: String,

    @Column(name = "STATUS")
    var status: Int,

    @Column(name = "CREATE_DATE")
    val createDate: Date,

    @Column(name = "LAST_UPDATE")
    var lastUpdate: Date,

    @Column(name = "CREATE_BY")
    var createBy:String,


    @Column(name = "NAMA_TRAVEL")
    var namaTravel: String,

    @Column(name = "NOMINAL_FEE")
    var nominalFee :Int,

    @Column(name = "ALAMAT")
    var alamat: String,

    @Column(name = "KOTA")
    var kota: String,

    @Column(name = "EMAIL")
    var email: String,

    @Column(name = "WEBSITE")
    var website: String,

    @Column(name = "LOGO_TRAVEL")
    var logoTravel: String,

    @Column(name = "BACKGROUND")
    var backround: String,

    @Column(name = "TELP")
    var telp: String
)
