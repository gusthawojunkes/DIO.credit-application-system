package solutions.it.wo.credit_application_system.entity

import jakarta.persistence.*
import solutions.it.wo.credit_application_system.dto.CustomerView
import java.math.BigDecimal

@Entity
data class Customer (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(nullable = false)
    var firstName: String = "",

    @Column(nullable = false)
    var lastName: String = "",

    @Column(nullable = false)
    val income: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val cpf: String = "",

    @Column(nullable = false)
    var email: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = false)
    @Embedded
    var address: Address,

    @Column(nullable = false)
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "customer")
    var credits: List<Credit> = mutableListOf()
) {
    fun toView(): CustomerView {
        return CustomerView(
            firstName = this.firstName,
            lastName = this.lastName,
            cpf = this.cpf,
            income = this.income,
            email = this.email,
            zipCode = this.address.zipCode,
            street = this.address.street
        )
    }
}