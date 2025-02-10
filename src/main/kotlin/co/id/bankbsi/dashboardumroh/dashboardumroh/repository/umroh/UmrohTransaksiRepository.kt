package co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh.UmrohTransaksi
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface UmrohTransaksiRepository : JpaRepository<UmrohTransaksi, String> {
    @Query("SELECT t FROM UmrohTransaksi t WHERE t.createDate >= :startDate AND t.createDate <= :endDate")
    fun findTransactionsByPeriod(
        @Param("startDate") startDate: String,
        @Param("endDate") endDate: String
    ): List<UmrohTransaksi>
}