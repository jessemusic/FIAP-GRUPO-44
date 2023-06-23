package br.com.fiap.grupo44.entrega.execption;

public class ControllerNotFoundException extends RuntimeException{

    public ControllerNotFoundException(String message){
        super(message);
    }


}
