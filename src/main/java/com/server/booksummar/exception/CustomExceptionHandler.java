package com.server.booksummar.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultException> handleResourceNotFoundException(MethodArgumentNotValidException ex) {
        DefaultException defaultException = new DefaultException();

        String message = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" - "));

        defaultException.setStatus(ex.getStatusCode().value());
        defaultException.setMensagem(message);
        defaultException.setZonedDateTime(ZonedDateTime.now());
        return ResponseEntity.status(defaultException.getStatus()).body(defaultException);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<DefaultException> handleNoSuchElementException(NoSuchElementException ex) {
        DefaultException defaultException = new DefaultException();
        defaultException.setStatus(HttpStatus.NOT_FOUND.value());
        defaultException.setMensagem(ex.getMessage());
        defaultException.setZonedDateTime(ZonedDateTime.now());
        return ResponseEntity.status(defaultException.getStatus()).body(defaultException);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DefaultException> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        DefaultException defaultException = new DefaultException();
        defaultException.setStatus(HttpStatus.CONFLICT.value());
        defaultException.setMensagem(ex.getMessage());
        defaultException.setZonedDateTime(ZonedDateTime.now());
        return ResponseEntity.status(defaultException.getStatus()).body(defaultException);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<DefaultException> handleTokenExpiredException(TokenExpiredException ex) {
        DefaultException defaultException = new DefaultException();
        defaultException.setStatus(HttpStatus.UNAUTHORIZED.value());
        defaultException.setMensagem("Token expirado em: " + ex.getExpiredOn());
        defaultException.setZonedDateTime(ZonedDateTime.now());
        return ResponseEntity.status(defaultException.getStatus()).body(defaultException);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<DefaultException> handleJWTVerificationException(JWTVerificationException ex) {
        DefaultException defaultException = new DefaultException();
        defaultException.setStatus(HttpStatus.BAD_REQUEST.value());
        defaultException.setMensagem("Falha na verificação do token");
        defaultException.setZonedDateTime(ZonedDateTime.now());
        return ResponseEntity.status(defaultException.getStatus()).body(defaultException);
    }

    @ExceptionHandler({Exception.class, DisabledException.class, BadCredentialsException.class, LockedException.class, AuthenticationException.class})
    public ResponseEntity<DefaultException> handleAuthenticationExceptions(Exception ex) {
        DefaultException defaultException = new DefaultException();
        defaultException.setZonedDateTime(ZonedDateTime.now());
        if (ex instanceof DisabledException) {
            defaultException.setMensagem(ex.getMessage());
            defaultException.setStatus(HttpStatus.UNAUTHORIZED.value());
            return ResponseEntity.status(defaultException.getStatus()).body(defaultException);
        } else if (ex instanceof BadCredentialsException) {
            defaultException.setMensagem(ex.getMessage());
            defaultException.setStatus(HttpStatus.UNAUTHORIZED.value());
            return ResponseEntity.status(defaultException.getStatus()).body(defaultException);
        } else if (ex instanceof LockedException) {
            defaultException.setMensagem(ex.getMessage());
            defaultException.setStatus(HttpStatus.UNAUTHORIZED.value());
            return ResponseEntity.status(defaultException.getStatus()).body(defaultException);
        } else if (ex instanceof AuthenticationException) {
            defaultException.setMensagem(ex.getMessage());
            defaultException.setStatus(HttpStatus.UNAUTHORIZED.value());
            return ResponseEntity.status(defaultException.getStatus()).body(defaultException);
        } else {
            defaultException.setMensagem(ex.getMessage());
            defaultException.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(defaultException.getStatus()).body(defaultException);
        }
    }

}
