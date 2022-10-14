package user.v1.model.entity

import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Column(unique = true, nullable = false)
    val email: String,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false, unique = true)
    val userId: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}