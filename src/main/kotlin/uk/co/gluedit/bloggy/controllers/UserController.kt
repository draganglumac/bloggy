package uk.co.gluedit.bloggy.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import uk.co.gluedit.bloggy.exceptions.UserNotFoundException
import uk.co.gluedit.bloggy.model.User
import uk.co.gluedit.bloggy.services.UserService

@RestController
class UserController(@Autowired val service: UserService) {

    @GetMapping("/users")
    fun getAllUsers(): List<User> = service.findAll()

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: Int): User {
        return service.findOne(id) ?: throw UserNotFoundException("id-${id}")
    }

    @PostMapping("/users")
    fun createUser(@RequestBody user: User): ResponseEntity<Void> {
        val savedUser = service.save(user)
        val location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(savedUser.id)
                .toUri()
        return ResponseEntity.created(location).build()
    }
}