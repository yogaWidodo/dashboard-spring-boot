package co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auth.AuthenticationRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.AuthenticationResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.TokenResponse
import org.springframework.security.core.userdetails.UserDetails

interface AuthenticationService {
    fun authentication(authRequest: AuthenticationRequest):AuthenticationResponse
    fun generateRefreshToken(user: UserDetails):String
    fun generateAccessToken(user: UserDetails):String
    fun refreshAccessToken(token: String):TokenResponse?
}