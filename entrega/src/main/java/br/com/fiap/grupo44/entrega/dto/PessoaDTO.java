package br.com.fiap.grupo44.entrega.dto;

import br.com.fiap.grupo44.entrega.entities.Pessoa;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private Character sexo;

    public PessoaDTO(Pessoa entidade){
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.dataNascimento = entidade.getDataNascimento();
        this.sexo = entidade.getSexo();
    }
}
