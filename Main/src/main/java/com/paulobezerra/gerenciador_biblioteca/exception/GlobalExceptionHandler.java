package com.paulobezerra.gerenciador_biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Erros de validação (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();
            erros.put(campo, mensagem);
        });

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "Erro de validação");
        resposta.put("campos", erros);
        resposta.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(resposta);
    }

    // Erros de regra de negócio (RuntimeException)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(
            RuntimeException ex) {

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", ex.getMessage());
        resposta.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(resposta);
    }

    // Erros inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(
            Exception ex) {

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        resposta.put("erro", "Erro interno no servidor");
        resposta.put("timestamp", LocalDateTime.now());

        return ResponseEntity.internalServerError().body(resposta);
    }
}