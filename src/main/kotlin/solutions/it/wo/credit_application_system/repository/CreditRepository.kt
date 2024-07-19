package solutions.it.wo.credit_application_system.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import solutions.it.wo.credit_application_system.entity.Credit
import java.util.*

@Repository
interface CreditRepository: JpaRepository<Credit, Long> {
    fun findByCreditCode(creditCode: UUID) : Credit?

    @Query("SELECT * FROM credit WHERE id = ?1", nativeQuery = true)
    fun findAllByCustomer(customerId: Long): List<Credit>
}

