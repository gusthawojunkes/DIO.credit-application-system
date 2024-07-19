package solutions.it.wo.credit_application_system.core.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(exception: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = mutableMapOf<String, String?>()
        exception.bindingResult.allErrors.forEach { error: ObjectError ->
            val fieldName: String = (error as FieldError).field
            val errorMessage: String = error.defaultMessage ?: "Unknown error"
            errors[fieldName] = errorMessage
        }

        return ResponseEntity(
            ErrorResponse(
                title = "Bad Request",
                message = "An error occurred",
                status = HttpStatus.BAD_REQUEST.value(),
                timestamp = System.currentTimeMillis(),
                details = errors
            ), HttpStatus.BAD_REQUEST
        )
    }
}