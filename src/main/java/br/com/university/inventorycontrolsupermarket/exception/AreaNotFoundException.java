package br.com.university.inventorycontrolsupermarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class AreaNotFoundException extends RuntimeException{

    public AreaNotFoundException(String message){
        super(message);
    }

}
