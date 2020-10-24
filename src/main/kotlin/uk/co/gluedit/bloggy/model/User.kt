package uk.co.gluedit.bloggy.model

import java.util.*
import javax.validation.constraints.*

class User(val id: Int?,
           @field:NotNull @field:Size(min = 2, message = "{name.minSize}") val name: String,
           @field:NotNull @field:Past(message = "{birthDate.past") val birthDate: Date
) {

    val posts: MutableList<Post> = mutableListOf()

    fun addPost(post: Post): Post {
        val savedPost = if (post.id != null) post else post.copy(id = posts.size + 1)
        posts.add(savedPost)
        return savedPost
    }

    fun findPost(id: Int): Post? = posts.find { p -> p.id == id }

    fun copy(id: Int?,
             name: String = this.name,
             birthDate: Date = this.birthDate
    ): User = User(id, name, birthDate)

}