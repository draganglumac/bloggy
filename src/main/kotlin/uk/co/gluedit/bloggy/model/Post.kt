package uk.co.gluedit.bloggy.model

import java.util.*

data class Post(
        val id: Int?,
        val date: Date = Date(),
        val message: String,
)