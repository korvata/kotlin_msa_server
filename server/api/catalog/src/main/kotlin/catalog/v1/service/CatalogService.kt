package catalog.v1.service

import catalog.v1.domain.dto.CatalogResponse

interface CatalogService {
    fun findAllCatalog(): List<CatalogResponse>
}