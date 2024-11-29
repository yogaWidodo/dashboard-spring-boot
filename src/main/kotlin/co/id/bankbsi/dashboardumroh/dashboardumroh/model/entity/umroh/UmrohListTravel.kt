package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "UMROH_LISTTRAVEL")
data class UmrohListTravel(
    @Id
    @Column(name = "ID_LIST")
    val idList: String,

    @Column(name = "KD_TRAVEL")
    val kdTravel: String,

    @Column(name = "NAMA_TRAVEL")
    val namaTravel: String,

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
    val background: String,

    @Column(name = "TELP")
    val telp: String


)
