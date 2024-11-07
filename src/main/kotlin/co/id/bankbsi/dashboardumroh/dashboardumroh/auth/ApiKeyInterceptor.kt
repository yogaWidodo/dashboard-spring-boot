package co.id.bankbsi.dashboardumroh.dashboardumroh.auth

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.UnauthorizedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.ApiKeyRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.Exception


//@Component
//class ApiKeyInterceptor(val apiKeyRepository: ApiKeyRepository) : HandlerInterceptor {
//    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
//        val requestURI = request.requestURI
//        if (requestURI == "/" || requestURI == "/index.html") {
//            return true
//        }
//
//        val apikey = request.getHeader("X-Api-Key")?:throw UnauthorizedException()
//        if (!apiKeyRepository.existsById(apikey)){
//            throw UnauthorizedException()
//        }
//        return true
//    }
//}