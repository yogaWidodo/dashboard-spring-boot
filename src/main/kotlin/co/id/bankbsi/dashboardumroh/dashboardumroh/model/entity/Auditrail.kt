package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity

import jakarta.persistence.*
import java.util.Date


@Entity
@Table(name = "table_auditrail")
data class Auditrail(

    @Id
    @Column(name = "id_auditrail")
    val auditrail:String,

    @Column(name = "create_at")
    var createAt:Date,

    @Column(name = "type_data")
    var typeData:String,

    @Column(name = "data_before")
    var dataBefore:String,

    @Column(name = "data_after")
    var dataAfter:String,


)