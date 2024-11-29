package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "UMROH_DETAILJAMAAH")
data class UmrohDetailJamaah(
    @Id
    @Column(name = "ID_TBLJAMAAH")
    val idTblJamaah:String,

    @Column(name = "ID_MASTER")
    val idMaster:String,

    @Column(name = "NIK")
    val nik:String,

    @Column(name = "NO_HP")
    val noHp:String,

    @Column(name = "NAMA")
    val nama:String,

    @Column(name = "JKEL")
    val jkel:String,

    @Column(name = "ID_JAMAAH")
    val idJamaah:String,

    @Column(name = "NO_REK_HAJI")
    val noRekHaji:String
)
