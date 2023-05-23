package br.com.university.inventorycontrolsupermarket.handler;

import br.com.university.inventorycontrolsupermarket.exception.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UnprocessableEntityHandler {

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<String> handlerUnprocessableEntity(UnprocessableEntityException ex){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }
}
