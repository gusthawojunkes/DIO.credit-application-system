package solutions.it.wo.credit_application_system.service

import org.springframework.stereotype.Service
import solutions.it.wo.credit_application_system.entity.Credit
import solutions.it.wo.credit_application_system.repository.CreditRepository
import solutions.it.wo.credit_application_system.service.`interface`.ICreditService
import java.util.*

@Service
class CreditService (
    private val repository: CreditRepository,
    private val customerService: CustomerService
) : ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.repository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        return this.repository.findAllByCustomer(customerId)
    }

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit = repository.findByCreditCode(creditCode) ?:  throw RuntimeException("Credit code $creditCode not found")

        return if (credit.customer?.id == customerId) credit else throw RuntimeException("")
    }
}