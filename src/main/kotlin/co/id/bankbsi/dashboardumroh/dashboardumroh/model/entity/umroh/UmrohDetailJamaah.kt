package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "UMROH_DETAILJAMAAH", schema = "UMROH123")
data class UmrohDetailJamaah(
    @Id
    @Column(name = "ID_TBLJAMAAH")
    var idTblJamaah:String,

    @Column(name = "ID_MASTER")
    var idMaster:String,

    @Column(name = "NIK")
    var nik:String,

    @Column(name = "NO_HP")
    var noHp:String,

    @Column(name = "NAMA")
    var nama:String,

    @Column(name = "JKEL")
    var jkel:String,

    @Column(name = "ID_JAMAAH")
    var idJamaah:String,

    @Column(name = "NO_REK_HAJI")
    var noRekHaji:String
)
