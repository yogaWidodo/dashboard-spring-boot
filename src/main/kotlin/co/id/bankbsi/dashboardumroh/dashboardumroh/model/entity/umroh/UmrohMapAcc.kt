package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Date

@Entity
@Table(name = "UMROH_MAP_ACC", schema = "UMROH123")
data class UmrohMapAcc(

    @Id
    @Column(name = "ID_MAP_ACC")
    val idMapAcc: Int,

    @Column(name = "ID_TRAVEL")
    val idTravel: String,

    @Column(name = "KD_TRAVEL")
    val kdTravel: String,

    @Column(name = "NO_REKENING")
    val noRekening: String,

    @Column(name = "STATUS")
    val status: Int,

    @Column(name = "CREATE_DATE")
    val createDate: Date,

    @Column(name = "LAST_UPDATE")
    val lastUpdate: Date,

    @Column(name = "CREATE_BY")
    val createBy:String,


    @Column(name = "NAMA_TRAVEL")
    val namaTravel: String,

    @Column(name = "NOMINAL_FEE")
    val nominalFee :Int,

    @Column(name = "ALAMAT")
    val alamat: String,

    @Column(name = "KOTA")
    val kota: String,

    @Column(name = "EMAIL")
    val email: String,

    @Column(name = "WEBSITE")
    val website: String,

    @Column(name = "LOGO_TRAVEL")
    val logoTravel: String,

    @Column(name = "BACKGROUND")
    val backround: String,

    @Column(name = "TELP")
    val telp: String
)
