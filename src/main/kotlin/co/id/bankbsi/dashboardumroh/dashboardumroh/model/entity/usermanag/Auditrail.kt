package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.AuditrailResponse
import jakarta.persistence.*
import java.util.Date


@Entity
@Table(name = "table_auditrail", schema = "UMROH123")
data class Auditrail(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditrail")
    val auditrail: Int = 0,

    @Column(name = "create_at")
    var createAt: Date,

    @Column(name = "type_data")
    var typeData: String,

    @Column(name = "data_before")
    var dataBefore: String,

    @Column(name = "data_after")
    var dataAfter: String,
)

fun Auditrail.mapToAuditrailResponse(): AuditrailResponse {
    return AuditrailResponse(
        idAuditrail = this.auditrail,
        createAt = this.createAt,
        typeData = this.typeData,
        dataAfter = this.dataAfter,
        dataBefore = this.dataBefore
    )
}