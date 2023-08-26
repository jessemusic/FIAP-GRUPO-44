package br.com.fiap.grupo44.entrega.dto;

import br.com.fiap.grupo44.entrega.entities.Eletrodomestico;
import br.com.fiap.grupo44.entrega.entities.Pessoa;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PessoaDTO {


    private Long id;

    @NotNull(message = "Nome não pode deve ser nulo")
    @NotBlank(message = "Você deve preenche um nome, não pode ser vazio")
    private String nome;


    @NotNull(message = "Sobrenome não pode deve ser nulo")
    private String sobrenome;

    private LocalDate dataNascimento;
    @NotNull(message = "Sexo não deve ser nulo")
    @NotBlank(message = "Sexo deve ser apenas um caractere")
    @Max(value = 1,message = "Sexo só pode ter uma letra 'M' para masculino  ou 'F' para feminino")
    private Character sexo;

    private Set<EletrodomesticoDTO> eletrodomesticos = new HashSet<>();

    public PessoaDTO(Pessoa entidade){
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.sobrenome = entidade.getSobrenome();
        this.dataNascimento = entidade.getDataNascimento();
        this.sexo = entidade.getSexo();
    }

    public PessoaDTO(Pessoa pessoa, Set<Eletrodomestico> eletrodomesticos) {
        this(pessoa);
        if (eletrodomesticos != null && eletrodomesticos.size() > 0){
            eletrodomesticos.forEach(eletro -> this.eletrodomesticos.add(new EletrodomesticoDTO(eletro)));
        }

    }
}
