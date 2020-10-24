package uk.co.gluedit.bloggy.exceptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
@RestController
class ErrorHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(
                Date(), ex.message!!, request.getDescription(false))
        return ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(UserNotFoundException::class, PostNotFoundException::class)
    fun handleMissingUser(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(
                Date(), ex.message!!, request.getDescription(false))
        return ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest
    ): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(Date(), "Validation error", ex.message)
        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException,
                                              headers: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest
    ): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(Date(), "Message format invalid", ex.localizedMessage)
        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }
}