package catalog.v1.domain.entity

import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EnableJpaAuditing
@Table(name = "catalog")
class CatalogEntity(
    @Column(nullable = false, unique = true, length = 120)
    val productId: String,
    @Column(nullable = false)
    var productName: String,
    @Column(nullable = false)
    var stock: Int,
    @Column(nullable = false)
    var unitPrice: Int,
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}