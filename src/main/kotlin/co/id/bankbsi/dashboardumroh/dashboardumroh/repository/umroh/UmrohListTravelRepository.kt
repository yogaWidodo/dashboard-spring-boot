package co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh.UmrohListTravel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UmrohListTravelRepository:JpaRepository<UmrohListTravel,String> {

    @Query("SELECT COUNT(idList) FROM UmrohListTravel")
    fun jumlahTravel():Int
}