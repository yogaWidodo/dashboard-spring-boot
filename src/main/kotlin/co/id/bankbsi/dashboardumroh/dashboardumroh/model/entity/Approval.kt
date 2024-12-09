package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "table_approval")
data class Approval(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_approval")
    val idApproval: Int = 0,

    @Column(name = "maker")
    var maker:String,

    @Column(name = "approver")
    var approver:String,

    @Column(name = "status")
    var status :String,

    @Column(name = "type_data")
    var typeData:String,

    @Column(name = "data_before")
    var dataBefore:String,

    @Column(name = "data_after")
    var dataAfter:String,

    @Column(name = "create_at")
    val createAt: Date,

    @Column(name = "update_at")
    var updateAt:Date,

    @Column(name = "remark_approval")
    var remarkApproval:String
    )