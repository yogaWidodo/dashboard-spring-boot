package co.id.bankbsi.dashboardumroh.dashboardumroh.config

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.ApiKey
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ApiKeySeeder(val apiKeyRepository: ApiKeyRepository) : ApplicationRunner {

    val apikey = "secret"
    override fun run(args: ApplicationArguments?) {
        if (!apiKeyRepository.existsById(apikey)){
            val entity = ApiKey(apiKey = apikey)
            apiKeyRepository.save(entity)
        }
    }

}