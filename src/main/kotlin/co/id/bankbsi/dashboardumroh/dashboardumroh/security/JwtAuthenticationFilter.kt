package co.id.bankbsi.dashboardumroh.dashboardumroh.security

import co.id.bankbsi.dashboardumroh.dashboardumroh.service.TokenService
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.UserService
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.CustomUserDetailService
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.TokenServiceImpl
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.Date

@Component
class JwtAuthenticationFilter(
    private val userDetailService: CustomUserDetailService,
    private val tokenService: TokenService,
    private val userService: UserService

) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")
        if (authHeader.doesNotContainBearerToken()) {
            filterChain.doFilter(request, response)
            return
        }
        val jwtToken = authHeader!!.extractTokenValue()
        try {
            val email = tokenService.extractEmail(jwtToken)
            if (email != null && SecurityContextHolder.getContext().authentication == null) {
                val foundUser = userDetailService.loadUserByUsername(email)
                if (tokenService.isValid(jwtToken, foundUser)) {
                    updateContext(foundUser, request)
                }
                filterChain.doFilter(request, response)
                userService.updateLastLogin(email, Date(), request)
            }
        } catch (e: ExpiredJwtException) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("${e.message}")
        } catch (e: MalformedJwtException) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            response.writer.write("Invalid JWT token")
        }


    }

    private fun updateContext(foundUser: UserDetails, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(
            foundUser,
            null,
            foundUser.authorities
        )
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken

    }

    private fun String?.doesNotContainBearerToken(): Boolean =
        this == null || !this.startsWith("Bearer ")

    private fun String.extractTokenValue(): String =
        this.substringAfter("Bearer ")


}