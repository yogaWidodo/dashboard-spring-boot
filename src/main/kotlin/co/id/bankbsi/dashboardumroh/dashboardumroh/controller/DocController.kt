package co.id.bankbsi.dashboardumroh.dashboardumroh.controller

import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DocController {

    @GetMapping("/api/doc", produces = [MediaType.TEXT_HTML_VALUE])
    fun getApiDoc(): ResponseEntity<String> {
        val resource = ClassPathResource("static/index.html") // Sesuaikan path jika berbeda
        val content = resource.inputStream.bufferedReader().use { it.readText() }
        return ResponseEntity.ok(content)
    }
}