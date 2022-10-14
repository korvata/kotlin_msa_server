package catalog.v1.controller

import catalog.v1.domain.dto.CatalogResponse
import catalog.v1.service.CatalogService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalog-service/v1")
class CatalogController(
    val catalogService: CatalogService
) {

    @GetMapping("/catalogs")
    fun findAllCatalog(): ResponseEntity<List<CatalogResponse>> {
        val catalogs = catalogService.findAllCatalog()
        return ResponseEntity.ok(catalogs)
    }
}