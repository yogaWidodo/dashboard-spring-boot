package co.id.bankbsi.dashboardumroh.dashboardumroh.controller

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.UnauthorizedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = constraintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unAuthorizedException(exception: UnauthorizedException):WebResponse<String>{
        return WebResponse(
            code = 401,
            status = "Unauthorized",
            data = "Please put your X-Api-Key"
        )
    }
}