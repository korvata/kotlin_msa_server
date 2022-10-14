package catalog.v1.repository

import catalog.v1.domain.entity.CatalogEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CatalogRepository : JpaRepository<CatalogEntity, String> {
}