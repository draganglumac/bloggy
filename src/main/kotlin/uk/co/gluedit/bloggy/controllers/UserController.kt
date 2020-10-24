package uk.co.gluedit.bloggy.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import uk.co.gluedit.bloggy.exceptions.PostNotFoundException
import uk.co.gluedit.bloggy.exceptions.UserNotFoundException
import uk.co.gluedit.bloggy.model.Post
import uk.co.gluedit.bloggy.model.User
import uk.co.gluedit.bloggy.services.UserService
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(@Autowired val service: UserService) {

    @GetMapping
    fun getAllUsers(): List<User> =
            service.findAll()

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int): EntityModel<User> {
        val user = service.findOne(id) ?: throw UserNotFoundException("userId-${id}")
        val resource = EntityModel.of(user)
        val linkTo = linkTo(methodOn(this::class.java).getAllUsers())
        resource.add(linkTo.withRel("all-users"))
        return resource
    }

    @PostMapping
    fun createUser(@RequestBody @Valid user: User): ResponseEntity<Void> {
        val savedUser = service.save(user)
        val location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(savedUser.id)
                .toUri()
        return ResponseEntity.created(location).build()
    }

    @GetMapping("/{userId}/posts")
    fun getPostsForUser(@PathVariable userId: Int): List<Post> =
            getUser(userId).content!!.posts

    @PostMapping("/{userId}/posts")
    fun addUserPost(@PathVariable userId: Int, @RequestBody post: Post): ResponseEntity<Void> {
        val user = getUser(userId).content!!
        val savedPost = user.addPost(post)
        val location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(savedPost.id!!)
                .toUri()
        return ResponseEntity.created(location).build()
    }

    @GetMapping("/{userId}/posts/{id}")
    fun getPost(@PathVariable userId: Int, @PathVariable id: Int): Post =
            getUser(userId).content!!.findPost(id) ?: throw PostNotFoundException("userId-${userId}, postId-${id}")

    @DeleteMapping("/{userId}")
    fun deletePost(@PathVariable userId: Int): User =
            service.deleteById(userId) ?: throw UserNotFoundException("userId-${userId}")
}