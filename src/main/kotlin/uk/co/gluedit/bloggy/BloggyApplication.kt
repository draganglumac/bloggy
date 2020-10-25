package uk.co.gluedit.bloggy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*

@SpringBootApplication
class BloggyApplication

fun main(args: Array<String>) {
	runApplication<BloggyApplication>(*args)
}

@Bean
fun localeResolver(): LocaleResolver {
	val localeResolver = AcceptHeaderLocaleResolver()
	localeResolver.defaultLocale = Locale.UK
	return localeResolver
}

@Bean
fun bundleMessageSource(): ResourceBundleMessageSource {
	val messageSource = ResourceBundleMessageSource()
	messageSource.setBasename("messages")
	return messageSource
}