package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "UMROH_SETTING")
data class UmrohSetting(

    @Id
    @Column(name = "ID_SETTING")
    val idSetting:Int,

    @Column(name = "KEY_SETTING")
    var keySetting:String,

    @Column(name = "VALUE_SETTING")
    var valueSetting :String
)
