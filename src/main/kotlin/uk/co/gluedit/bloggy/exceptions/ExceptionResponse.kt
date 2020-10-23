package uk.co.gluedit.bloggy.exceptions

import java.util.*

data class ExceptionResponse(
        val timestamp: Date,
        val message: String,
        val details: String,
)