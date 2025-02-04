package co.id.bankbsi.dashboardumroh.dashboardumroh.controller

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.DataAlreadyAssignedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.ForbiddenException
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.UnauthorizedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@CrossOrigin(originPatterns = ["*"])
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): ResponseEntity<WebResponse<String>> {
        return ResponseEntity.badRequest().body(
            WebResponse(
                code = 400,
                status = "BAD REQUEST",
                data = constraintViolationException.message!!
            )
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundException(notFoundException: NotFoundException): ResponseEntity<WebResponse<String>> {
        return ResponseEntity.status(404).body(
            WebResponse(
                code = 404,
                status = "NOT FOUND",
                data = "Not Found"
            )
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unauthorizedException(unauthorizedException: UnauthorizedException): ResponseEntity<WebResponse<String>> {
        return ResponseEntity.status(401).body(
            WebResponse(
                code = 401,
                status = "UNAUTHORIZED",
                data = "Unauthorized"
            )
        )
    }

    @ExceptionHandler(value = [ForbiddenException::class])
    fun forbiddenException(forbidden: ForbiddenException): ResponseEntity<WebResponse<String>> {
        return ResponseEntity.status(403).body(
            WebResponse(
                code = 403,
                status = "FORBIDDEN",
                data = "Forbidden"
            )
        )
    }

    @ExceptionHandler(value = [DataAlreadyAssignedException::class])
    fun dataAlreadyAssignedException(dataAlreadyAssignedException: DataAlreadyAssignedException): ResponseEntity<WebResponse<String>> {
        return ResponseEntity.status(409).body(
            WebResponse(
                code = 409,
                status = "CONFLICT",
                data = "Data Already Assigned"
            )
        )
    }
}