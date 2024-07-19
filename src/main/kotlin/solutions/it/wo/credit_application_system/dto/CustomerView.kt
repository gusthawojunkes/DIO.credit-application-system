package solutions.it.wo.credit_application_system.dto

import java.math.BigDecimal

data class CustomerView (
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val zipCode: String,
    val street: String
)
