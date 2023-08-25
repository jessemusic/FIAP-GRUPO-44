package br.com.fiap.grupo44.entrega.dominio.pessoa.dto;

import br.com.fiap.grupo44.entrega.dominio.pessoa.entities.Pessoa;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaDTO {


    private Long id;

    @NotNull(message = "Nome não pode deve ser nulo")
    @NotBlank(message = "Você deve preenche um nome, não pode ser vazio")
    private String nome;


    @NotNull(message = "Sobrenome não pode deve ser nulo")
    private String sobrenome;

    private String dataNascimento;
    @NotNull(message = "Sexo não deve ser nulo")
    @NotBlank(message = "Sexo deve ser apenas um caractere")
    @Max(value = 1,message = "Sexo só pode ter uma letra 'M' para masculino  ou 'F' para feminino")
    private String sexo;
    private Integer idade;
    private String email;
    private String phone;
    private String cell;
    private String fotosUrls;
    private String nat;

    public PessoaDTO(Pessoa entidade){
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.sobrenome = entidade.getSobrenome();
        this.dataNascimento = entidade.getDataNascimento();
        this.sexo = entidade.getSexo();
        this.idade = entidade.getIdade();
        this.email = entidade.getEmail();
        this.phone = entidade.getPhone();
        this.cell = entidade.getCell();
        this.fotosUrls = entidade.getFotosUrls();
        this.nat = entidade.getNat();
    }
}
