package uk.co.gluedit.bloggy.model

import java.util.*

data class User(
        val id: Int?,
        val name: String,
        val birthDate: Date,
        val posts: MutableList<Post> = mutableListOf(),
) {

    fun addPost(post: Post): Post {
        val savedPost = if (post.id != null) post else post.copy(id = posts.size + 1)
        posts.add(savedPost)
        return savedPost
    }

    fun findPost(id: Int): Post? = posts.find { p -> p.id == id }

}