package br.com.fiap.grupo44.entrega.exception;

public class ControllerNotFoundException extends RuntimeException{

    public ControllerNotFoundException(String message){
        super(message);
    }


}
