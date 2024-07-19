package solutions.it.wo.credit_application_system.entity

import jakarta.persistence.*
import solutions.it.wo.credit_application_system.core.enums.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Entity
data class Credit (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false)
    val creditCode: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val creditValue: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val dayFirstInstallment: LocalDate,

    @Column(nullable = false)
    val numberOfInstallments: Int = 0,

    @Enumerated
    val status: Status,

    @ManyToOne
    var customer: Customer? = null,

)
