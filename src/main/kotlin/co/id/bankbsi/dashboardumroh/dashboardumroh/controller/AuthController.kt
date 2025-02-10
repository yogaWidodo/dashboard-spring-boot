package co.id.bankbsi.dashboardumroh.dashboardumroh.controller

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auth.AuthenticationRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auth.RefreshTokenRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.auth.AuthenticationResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.auth.TokenResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.AuthenticationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(originPatterns = ["*"])
class AuthController(
    private val authenticationService: AuthenticationService,
) {
    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse =
        authenticationService.authentication(authRequest)

    @PostMapping("/refresh")
    fun refreshToken(@RequestBody request: RefreshTokenRequest): TokenResponse? =
        authenticationService.refreshAccessToken(request.token)

}
