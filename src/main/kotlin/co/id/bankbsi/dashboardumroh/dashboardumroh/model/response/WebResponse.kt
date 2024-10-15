package co.id.bankbsi.dashboardumroh.dashboardumroh.model.response

data class WebResponse<T> (
    val code:Int,
    val status:String,
    val data:T
)