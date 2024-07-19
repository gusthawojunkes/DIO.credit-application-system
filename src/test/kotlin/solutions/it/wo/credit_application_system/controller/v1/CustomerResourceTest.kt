package solutions.it.wo.credit_application_system.controller.v1

import com.google.gson.Gson
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import solutions.it.wo.credit_application_system.dto.CustomerDTO
import solutions.it.wo.credit_application_system.entity.Address
import solutions.it.wo.credit_application_system.entity.Customer
import solutions.it.wo.credit_application_system.repository.CustomerRepository
import java.math.BigDecimal

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CustomerResourceTest {

    @Autowired private lateinit var repository: CustomerRepository
    @Autowired private lateinit var mockMvc: MockMvc
    @Autowired private lateinit var gson: Gson

    companion object {
        const val URL = "/api/v1/customers"
    }

    @BeforeEach fun setup() = repository.deleteAll()

    @AfterEach fun tearDown() = repository.deleteAll()

    @Test
    fun `should save customer and return 201 status`() {
        val customerDTO = CustomerDTO(
            firstName = "John",
            lastName = "Doe",
            cpf = "12345678900",
            income = BigDecimal(1000.0),
            email = "john.doe@example.com",
            password = "123456",
            zipCode = "12345678",
            street = "Main Street"
        )
        mockMvc.perform(post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(gson.toJson(customerDTO)))
            .andExpect(status().isCreated)
    }

    @Test
    fun `should find customer by ID and return 200 status`() {
        repository.save(fakeCustomer())
        mockMvc.perform(get("$URL/{id}", 1))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.firstName").value("John"))
    }

    @Test
    fun `should delete customer and return 204 status`() {
        mockMvc.perform(delete("$URL/{id}", 1))
            .andExpect(status().isNoContent)
    }

    @Test
    fun `should update customer and return 200 status`() {
        val savedCustomer = repository.save(fakeCustomer())
        val customerDTO = fakeCustomerDTO()

        mockMvc.perform(patch("$URL/{id}", savedCustomer.id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(gson.toJson(customerDTO)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.firstName").value("Jane"))
            .andExpect(jsonPath("$.lastName").value("Doe"))
            .andExpect(jsonPath("$.email").value("jane.doe@example.com"))
    }

    private fun fakeCustomerDTO(): CustomerDTO {
        val customerDTO = CustomerDTO(
            firstName = "Jane",
            lastName = "Doe",
            cpf = "12345678900",
            income = BigDecimal(2000.0),
            email = "jane.doe@example.com",
            password = "123456",
            zipCode = "12345678",
            street = "Main Street"
        )
        return customerDTO
    }

    fun fakeCustomer(): Customer {
        return Customer(
            id = null,
            firstName = "John",
            lastName = "Doe",
            cpf = "12345678900",
            income = BigDecimal(1000.0),
            email = "teste@teste.com",
            password = "123456",
            address = Address(zipCode = "12345678", street = "Main Street")
        )
    }

}