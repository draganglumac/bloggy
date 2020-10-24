package uk.co.gluedit.bloggy.model

import java.util.*

class Post(
        val id: Int?,
        val date: Date = Date(),
        val message: String
) {

    fun copy(id: Int,
             date: Date = this.date,
             message: String = this.message
    ): Post = Post(id, date, message)
}