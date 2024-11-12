package co.id.bankbsi.dashboardumroh.dashboardumroh.validation

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.User
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validator
import org.springframework.stereotype.Component

@Component
class ValidationUtill(val validator:Validator) {
    fun validate(any: Any){
        val result = validator.validate(any)

        if(result.size != 0){
            throw ConstraintViolationException(result)
        }
    }


}