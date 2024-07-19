package solutions.it.wo.credit_application_system.service.`interface`

import solutions.it.wo.credit_application_system.entity.Customer

interface ICustomerService {

    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(id: Long)

}