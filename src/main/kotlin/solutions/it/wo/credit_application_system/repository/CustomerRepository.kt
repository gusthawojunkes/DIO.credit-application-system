package solutions.it.wo.credit_application_system.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import solutions.it.wo.credit_application_system.entity.Customer

@Repository
interface CustomerRepository : JpaRepository<Customer, Long>