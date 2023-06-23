package br.com.fiap.grupo44.entrega.exception;

import lombok.Data;

import java.time.Instant;
@Data
public class DefaultError {

    private Instant timeStamp;
    private  Integer status;

    private String error;

    private String message;

    private String path;

}
