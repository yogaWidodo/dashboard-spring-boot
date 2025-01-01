package co.id.bankbsi.dashboardumroh.dashboardumroh.model.response

data class WebResponse<T>(
    val code: Int,
    val status: String,
    val data: T
)


data class WebResponseApi<T>(
    val code: Int,
    val status: String,
    val data: T?,
    val message: String? = null
)

sealed class DefaultResponse<T> {
    data class Success<T>(val data: T) : DefaultResponse<T>()
    data class Error<T>(val message: String, val e: Exception) : DefaultResponse<T>()
}



