package solutions.it.wo.credit_application_system.core.exceptions

data class ErrorResponse (
    val title: String,
    val message: String,
    val status: Int,
    val timestamp: Long,
    val details: MutableMap<String, String?> = mutableMapOf()
)
