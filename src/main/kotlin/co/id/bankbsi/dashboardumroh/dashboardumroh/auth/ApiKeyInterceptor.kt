package co.id.bankbsi.dashboardumroh.dashboardumroh.auth

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.UnauthorizedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.ApiKeyRepository
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

//@Component
//class ApiKeyInterceptor(val apiKeyRepository: ApiKeyRepository):WebRequestInterceptor {
//    override fun preHandle(request: WebRequest) {
//        val apikey = request.getHeader("X-Api-key")?:throw UnauthorizedException()
//        if (!apiKeyRepository.existsById(apikey)){
//            throw UnauthorizedException()
//        }
//
//    }
//
//    override fun postHandle(request: WebRequest, model: ModelMap?) {
//    }
//
//    override fun afterCompletion(request: WebRequest, ex: Exception?) {
//    }
//
//}