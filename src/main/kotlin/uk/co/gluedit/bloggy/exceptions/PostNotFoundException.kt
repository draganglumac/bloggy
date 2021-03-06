package uk.co.gluedit.bloggy.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
data class PostNotFoundException(override val message: String) : RuntimeException(message)
