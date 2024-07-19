package solutions.it.wo.credit_application_system.controller.v1

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import solutions.it.wo.credit_application_system.dto.CustomerDTO
import solutions.it.wo.credit_application_system.dto.CustomerView
import solutions.it.wo.credit_application_system.entity.Customer
import solutions.it.wo.credit_application_system.service.CustomerService

@RestController
@RequestMapping("/api/v1/customers")
class CustomerResource (
    private val service: CustomerService
) {

    @PostMapping
    fun save(@RequestBody customer: CustomerDTO): ResponseEntity<Unit> {
        this.service.save(customer.toEntity())

        return ResponseEntity.status(201).build()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<CustomerView> {
        val customer = try {this.service.findById(id) } catch (e: Exception) { null }

        return if (customer == null) ResponseEntity.notFound().build() else ResponseEntity.ok().body(customer.toView())
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        this.service.delete(id)

        return ResponseEntity.noContent().build()
    }


    @PatchMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody customerToUpdate: CustomerDTO): ResponseEntity<CustomerView> {
        var customer: Customer = this.service.findById(id)

        customer = customer.copy(
            firstName = customerToUpdate.firstName,
            lastName = customerToUpdate.lastName,
            income = customerToUpdate.income,
            email = customerToUpdate.email
        )

        customer = this.service.save(customer)

        return ResponseEntity.ok().body(customer.toView())
    }

}