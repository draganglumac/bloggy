package uk.co.gluedit.bloggy.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import uk.co.gluedit.bloggy.model.HelloWorldBean

@RestController
class HelloWorldController {

    @GetMapping("hello-world")
    fun helloWorld(): String {
        return "Hello world!"
    }

    @GetMapping("hello-world-bean")
    fun helloWorldBean(): HelloWorldBean {
        return HelloWorldBean("Hello world!")
    }

    @GetMapping("hello/{name}")
    fun hello(@PathVariable name: String): HelloWorldBean {
        return HelloWorldBean("Hello ${name}!")
    }
}