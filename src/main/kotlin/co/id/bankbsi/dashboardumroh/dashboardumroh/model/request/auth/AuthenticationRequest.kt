package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auth

data class AuthenticationRequest(
    val userLdap: String,
    val password: String
)
