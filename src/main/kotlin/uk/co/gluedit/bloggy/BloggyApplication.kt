package uk.co.gluedit.bloggy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BloggyApplication

fun main(args: Array<String>) {
	runApplication<BloggyApplication>(*args)
}
