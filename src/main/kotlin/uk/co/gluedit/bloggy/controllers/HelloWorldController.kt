package uk.co.gluedit.bloggy.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import uk.co.gluedit.bloggy.model.HelloWorldBean
import java.util.*

@RestController
class HelloWorldController(@Autowired val messageSource: MessageSource) {

    @GetMapping("hello-world")
    fun helloWorld() = messageSource.getMessage("hello.world", null, LocaleContextHolder.getLocale())

    @GetMapping("hello-world-bean")
    fun helloWorldBean() = HelloWorldBean(helloWorld())

    @GetMapping("hello/{name}")
    fun hello(@PathVariable name: String) = HelloWorldBean(
            "${messageSource.getMessage("hello", null, LocaleContextHolder.getLocale())} ${name}!"
    )
}