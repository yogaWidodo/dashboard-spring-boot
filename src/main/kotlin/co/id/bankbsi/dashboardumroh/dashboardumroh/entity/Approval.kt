package co.id.bankbsi.dashboardumroh.dashboardumroh.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "table_approval")
data class Approval(

    @Id
    @Column(name = "id_approval")
    val idApproval: String,

    @Column(name = "maker")
    val maker:String,

    @Column(name = "approver")
    val approver:String,

    @Column(name = "status")
    val status :String,

    @Column(name = "type_data")
    val typeData:String,

    @Column(name = "data_before")
    val dataBefore:String,

    @Column(name = "data_after")
    val dataAfter:String,

    @Column(name = "create_at")
    val createAt: Date,

    @Column(name = "update_at")
    val updateAt:Date,

    @Column(name = "remark_approval")
    val remarkApproval:String
    )