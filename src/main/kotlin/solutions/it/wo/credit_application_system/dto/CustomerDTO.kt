package solutions.it.wo.credit_application_system.dto

import solutions.it.wo.credit_application_system.entity.Address
import solutions.it.wo.credit_application_system.entity.Customer
import java.math.BigDecimal

data class CustomerDTO (
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val password: String,
    val zipCode: String,
    val street: String
) {
    fun toEntity(): Customer {
        return Customer (
            id = null,
            firstName = this.firstName,
            lastName = this.lastName,
            cpf = this.cpf,
            income = this.income,
            email = this.email,
            password = this.password,
            address = Address(zipCode = this.zipCode, street = this.street)
        )
    }
}


