package solutions.it.wo.credit_application_system.controller.v1

import org.springframework.web.bind.annotation.*
import solutions.it.wo.credit_application_system.dto.CreditDTO
import solutions.it.wo.credit_application_system.service.CreditService

@RestController
@RequestMapping("/api/v1/credit")
class CreditResource (
    private val service: CreditService
) {
    @PostMapping
    fun save(@RequestBody credit: CreditDTO) {
        this.service.save(credit.toEntity())
    }

}