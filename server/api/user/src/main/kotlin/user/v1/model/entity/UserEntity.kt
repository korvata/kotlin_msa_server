package user.v1.model.entity

import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EnableJpaAuditing
@Table(name = "users")
class UserEntity(
    @Column(unique = true, nullable = false)
    val email: String,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false, unique = true)
    val userId: String,
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}