package br.com.fiap.grupo44.entrega.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Pessoa {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private Character sexo;

}
