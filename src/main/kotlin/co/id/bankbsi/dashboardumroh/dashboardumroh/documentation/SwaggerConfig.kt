package co.id.bankbsi.dashboardumroh.dashboardumroh.documentation

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Dashboard Monitoring Umroh Documentation")
                    .version("1.0.0")
                    .description("This is a  API documented with Swagger and SpringDoc.")
            )
    }
}