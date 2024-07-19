package solutions.it.wo.credit_application_system.service

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import solutions.it.wo.credit_application_system.entity.Address
import solutions.it.wo.credit_application_system.entity.Customer
import solutions.it.wo.credit_application_system.repository.CustomerRepository
import java.math.BigDecimal

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK lateinit var repository: CustomerRepository
    @InjectMockKs lateinit var service: CustomerService

    @Test
    @DisplayName("should save customer")
    fun `should save customer`() {
        val customer = fakeCustomer()
        every { repository.save(any()) } returns customer
        assertEquals(customer, service.save(customer))

        verify(exactly = 1) { repository.save(customer) }
    }

    @Test
    @DisplayName("should find customer by ID")
    fun `should find customer by ID`() {
        val customer = fakeCustomer()
        every { repository.findById(1L) } returns java.util.Optional.of(customer)

        assertEquals(customer, service.findById(1L))

        verify(exactly = 1) { repository.findById(1L) }
    }

    @Test
    @DisplayName("should throw exception when customer ID not found")
    fun `should throw exception when customer ID not found`() {
        every { repository.findById(any()) } returns java.util.Optional.empty()

        assertThrows<RuntimeException> {
            service.findById(999L)
        }

        verify(exactly = 1) { repository.findById(999L) }
    }

    @Test
    @DisplayName("should delete customer")
    fun `should delete customer`() {
        every { repository.deleteById(1L) } just Runs

        service.delete(1L)

        verify(exactly = 1) { repository.deleteById(1L) }
    }

    fun fakeCustomer(): Customer {
        return Customer(
            id = 1,
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