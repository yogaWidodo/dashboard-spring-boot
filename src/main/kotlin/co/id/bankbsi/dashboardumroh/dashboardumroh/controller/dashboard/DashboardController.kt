package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.dashboard

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.dashboard.DashTransaksiPeriodResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.dashboard.DashTransaksiService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(originPatterns = ["*"])
class DashboardController(private val service: DashTransaksiService) {

    @GetMapping(
        value = ["/transaksi-data"],
        produces = ["application/json"]
    )
    fun getTransaksiData(
        @RequestParam("startDate") startDate: String,
        @RequestParam("endDate") endDate: String,
    ): WebResponse<List<DashTransaksiPeriodResponse>> {
        val data = service.getTransaksiByPeriode(startDate, endDate)
        return WebResponse(
            code = 200,
            status = "OK",
            data = data
        )
    }

}