package solutions.it.wo.credit_application_system.dto

import solutions.it.wo.credit_application_system.core.enums.Status
import solutions.it.wo.credit_application_system.entity.Credit
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class CreditDTO (

    val creditCode: UUID,

    val creditValue: BigDecimal,

    val dayFirstInstallment: LocalDate,

    val numberOfInstallments: Int,

    val status: Status,

    var customerId: Long?

) {
    fun toEntity(): Credit {
        return Credit(
            id = null,
            creditCode = this.creditCode,
            creditValue = this.creditValue,
            dayFirstInstallment = this.dayFirstInstallment,
            numberOfInstallments = this.numberOfInstallments,
            status = this.status
        )
    }
}