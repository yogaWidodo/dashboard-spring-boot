package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date


@Entity
@Table(name = "table_auditrail")
data class Auditrail(

    @Id
    @Column(name = "id_auditrail")
    val auditrail:String,

    @Column(name = "create_at")
    val createAt:Date,

    @Column(name = "type_data")
    val typeData:String,

    @Column(name = "data_before")
    val dataBefore:String,

    @Column(name = "data_after")
    val dataAfter:String,


)