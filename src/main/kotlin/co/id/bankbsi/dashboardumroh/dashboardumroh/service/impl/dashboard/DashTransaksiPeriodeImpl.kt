package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.dashboard

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.dashboard.DashTransaksiPeriodResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh.UmrohTransaksiRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.dashboard.DashTransaksiService
import org.springframework.stereotype.Service

@Service
class DashTransaksiPeriodeImpl(
    private val transaksiRepository: UmrohTransaksiRepository
) : DashTransaksiService {
    override fun getTransaksiByPeriode(startDate: String, endDate: String): List<DashTransaksiPeriodResponse> {
        val transactions = transaksiRepository.findTransactionsByPeriod(startDate, endDate)
        return transactions.groupBy { it.createDate }
            .map { (period, transactions) ->
                DashTransaksiPeriodResponse(
                    period = period!!,
                    transactionCount = transactions.size
                )
            }
    }

}