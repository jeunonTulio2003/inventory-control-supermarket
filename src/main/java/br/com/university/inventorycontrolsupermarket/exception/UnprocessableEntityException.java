package br.com.university.inventorycontrolsupermarket.exception;

public class UnprocessableEntityException extends RuntimeException{

    public UnprocessableEntityException(String message){
        super(message);
    }

}
