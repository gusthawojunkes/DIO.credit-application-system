package solutions.it.wo.credit_application_system.repository

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import solutions.it.wo.credit_application_system.core.enums.Status
import solutions.it.wo.credit_application_system.entity.Address
import solutions.it.wo.credit_application_system.entity.Credit
import solutions.it.wo.credit_application_system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {

    @Autowired lateinit var repository: CreditRepository
    @Autowired lateinit var entityManager: TestEntityManager
    private lateinit var customer: Customer
    private lateinit var credit: Credit


    @BeforeEach
    fun setup() {
        val customer = fakeCustomer()
        val credit = fakeCredit().apply { this.customer = customer }

        this.customer = customer
        this.credit = credit

        entityManager.persist(customer)
        entityManager.persist(credit)
    }

    @Test
    fun `should find credit by credit code`() {
        val foundCredit = repository.findByCreditCode(credit.creditCode)
        assertNotNull(foundCredit)
        assertEquals(credit.creditCode, foundCredit?.creditCode)
    }

    @Test
    fun `should find all credits by customer id`() {
        val foundCredits = repository.findAllByCustomer(customer.id!!)
        assertEquals(1, foundCredits.size)
        assertEquals(customer.id, foundCredits[0].customer?.id)
    }

    fun fakeCredit(): Credit {
        return Credit(
            id = null,
            creditCode = UUID.randomUUID(),
            customer = fakeCustomer(),
            status = Status.IN_PROGRESS,
            creditValue = BigDecimal(1000),
            dayFirstInstallment = LocalDate.now(),
            numberOfInstallments = 10
        )
    }

    fun fakeCustomer(): Customer {
        return Customer(
            id = null,
            firstName  = "John Doe",
            cpf = "12345678900",
            income = BigDecimal(1000),
            address = Address(
                street = "Rua 1",
                zipCode = "12345678"
            )
        )
    }

}