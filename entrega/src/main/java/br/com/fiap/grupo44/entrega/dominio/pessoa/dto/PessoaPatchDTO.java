package br.com.fiap.grupo44.entrega.dominio.pessoa.dto;

import br.com.fiap.grupo44.entrega.dominio.pessoa.entities.Pessoa;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaPatchDTO {


    private Long id;

    private String nome;

    private String sobrenome;

    private String dataNascimento;
    private String sexo;

    public PessoaPatchDTO(Pessoa entidade){
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.sobrenome = entidade.getSobrenome();
        this.dataNascimento = entidade.getDataNascimento();
        this.sexo = entidade.getSexo();
    }
}
