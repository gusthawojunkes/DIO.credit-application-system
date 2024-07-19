package solutions.it.wo.credit_application_system.service

import org.springframework.stereotype.Service
import solutions.it.wo.credit_application_system.entity.Customer
import solutions.it.wo.credit_application_system.repository.CustomerRepository
import solutions.it.wo.credit_application_system.service.`interface`.ICustomerService

@Service
class CustomerService (
    private val repository: CustomerRepository
) : ICustomerService {
    override fun save(customer: Customer): Customer = this.repository.save(customer)

    override fun findById(id: Long): Customer {
        return this.repository.findById(id).orElseThrow {
            throw RuntimeException("Id $id not found")
        }
    }

    override fun delete(id: Long) {
        this.repository.deleteById(id);
    }
}