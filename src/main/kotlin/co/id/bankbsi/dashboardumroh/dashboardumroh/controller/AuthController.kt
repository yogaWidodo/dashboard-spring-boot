package co.id.bankbsi.dashboardumroh.dashboardumroh.controller

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auth.AuthenticationRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auth.RefreshTokenRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.auth.AuthenticationResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.auth.TokenResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.AuthenticationService
import org.springframework.web.bind.annotation.*

/**
 * AuthController handles authentication-related HTTP requests.
 *
 * @param authenticationService the service used to handle authentication
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(originPatterns = ["*"])
class AuthController(
    private val authenticationService: AuthenticationService,
) {

    /**
     * Authenticates a user.
     *
     * @param authRequest the request body containing authentication details
     * @return an AuthenticationResponse containing the authentication result
     */
    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse =
        authenticationService.authentication(authRequest)

    /**
     * Refreshes an access token.
     *
     * @param request the request body containing the refresh token
     * @return a TokenResponse containing the new access token, or null if the refresh token is invalid
     */
    @PostMapping("/refresh")
    fun refreshToken(@RequestBody request: RefreshTokenRequest): TokenResponse? =
        authenticationService.refreshAccessToken(request.token)
}