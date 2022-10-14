package user.v1.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import user.v1.model.dto.RegisterUserRequest
import user.v1.model.dto.UserResponse
import user.v1.service.UserService
import javax.validation.Valid

@Controller
@RequestMapping("/user-service/v1")
class UserController(
    private val userService: UserService
    ) {

    //유저 전체 조회
    @GetMapping("/users")
    fun getUsers() {

    }

    //회원가입
    @PostMapping("/signUp")
    fun signUpUser(@RequestBody @Valid request: RegisterUserRequest): ResponseEntity<UserResponse> {
        val userResponse = userService.addUser(request)
        return ResponseEntity.ok(userResponse)
    }
}