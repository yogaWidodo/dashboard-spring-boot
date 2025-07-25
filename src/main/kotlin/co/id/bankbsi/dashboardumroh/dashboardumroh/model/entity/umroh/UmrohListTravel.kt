package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohListTravelResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "UMROH_LISTTRAVEL", schema = "UMROH123")
data class UmrohListTravel(
    @Id
    @Column(name = "ID_LIST")
    val idList: String,

    @Column(name = "KD_TRAVEL")
    var kdTravel: String,

    @Column(name = "NAMA_TRAVEL")
    var namaTravel: String,

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
    var background: String,

    @Column(name = "TELP")
    var telp: String
)
fun UmrohListTravel.toResponse(): UmrohListTravelResponse =
    UmrohListTravelResponse(
        idList = this.idList,
        kdTravel = this.kdTravel,
        namaTravel = this.namaTravel,
        alamat = this.alamat,
        kota = this.kota,
        email = this.email,
        website = this.website,
        logoTravel = this.logoTravel,
        background = this.background,
        telp = this.telp
    )