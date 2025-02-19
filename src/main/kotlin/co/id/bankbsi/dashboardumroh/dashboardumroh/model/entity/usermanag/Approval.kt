package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.ApprovalResponse
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "table_approval", schema = "UMROH123")
data class Approval(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_approval")
    val idApproval: Int = 0,

    @Column(name = "maker")
    var maker: String,

    @Column(name = "approver")
    var approver: String,

    @Column(name = "status")
    var status: String,

    @Column(name = "type_data")
    var typeData: String,

    @Column(name = "data_before", length = 500)
    var dataBefore: String,

    @Column(name = "data_after", length = 500)
    var dataAfter: String,

    @Column(name = "create_at")
    val createAt: Date,

    @Column(name = "update_at")
    var updateAt: Date,

    @Column(name = "remark_approval", length = 500)
    var remarkApproval: String
)

fun Approval.mapToApprovalResponse(): ApprovalResponse {
    return ApprovalResponse(
        idApproval = idApproval,
        maker = maker,
        approver = approver,
        status = status,
        typeData = typeData,
        dataBefore = dataBefore,
        dataAfter = dataAfter,
        createAt = createAt,
        updateAt = updateAt,
        remarkApproval = remarkApproval
    )
}