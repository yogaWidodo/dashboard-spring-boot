package co.id.bankbsi.dashboardumroh.dashboardumroh.service.dashboard

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.dashboard.DashTransaksiPeriodResponse

interface DashTransaksiService {
    fun getTransaksiByPeriode(
        startDate: String,
        endDate: String,
    ): List<DashTransaksiPeriodResponse>

}