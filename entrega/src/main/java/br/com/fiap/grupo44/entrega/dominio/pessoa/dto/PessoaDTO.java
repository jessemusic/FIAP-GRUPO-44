package br.com.fiap.grupo44.entrega.dominio.pessoa.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.dto.EletrodomesticoDTO;
import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.entities.Eletrodomestico;
import br.com.fiap.grupo44.entrega.dominio.endereco.dto.EnderecoDTO;
import br.com.fiap.grupo44.entrega.dominio.endereco.entities.Endereco;
import br.com.fiap.grupo44.entrega.dominio.pessoa.entities.Pessoa;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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
    @Max(value = 1, message = "Sexo só pode ter uma letra 'M' para masculino  ou 'F' para feminino")
    private String sexo;
    private Integer idade;
    private String email;
    private String phone;
    private String cell;
    private String fotosUrls;
    private String nat;
    @Null(message = "O valor do somatorio deve ser calculado automaticamente")
    private Double somatorioCustoMensal;
    private List<EletrodomesticoDTO> eletrodomesticos = new ArrayList<>();
    private List<EnderecoDTO> enderecos = new ArrayList<>();

    public PessoaDTO(Pessoa entidade) {
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
        this.somatorioCustoMensal = entidade.getSomatorioCustoMensal();
    }

    public PessoaDTO(Pessoa pessoa, Set<Eletrodomestico> eletrodomesticos) {
        this(pessoa);
        if (eletrodomesticos != null && eletrodomesticos.size() > 0) {
            Double somaCustoMensal = 0d;

            for(Eletrodomestico eletrodomestico: eletrodomesticos){
                somaCustoMensal += eletrodomestico.getCustoMensal();

                this.eletrodomesticos.add(new EletrodomesticoDTO(eletrodomestico));
            }
                this.somatorioCustoMensal = somaCustoMensal;
        }

    }

    public PessoaDTO(Pessoa pessoa, Set<Eletrodomestico> eletrodomesticos, Set<Endereco> enderecos) {
        this(pessoa);

        if (eletrodomesticos != null && eletrodomesticos.size() > 0) {
            Double somaCustoMensal = 0d;

            for(Eletrodomestico eletrodomestico: eletrodomesticos){
                somaCustoMensal += eletrodomestico.getCustoMensal();

                this.eletrodomesticos.add(new EletrodomesticoDTO(eletrodomestico));
            }
            this.somatorioCustoMensal = somaCustoMensal;
        }

        if (enderecos != null && enderecos.size() > 0) {

            for(Endereco endereco: enderecos){
                this.enderecos.add(new EnderecoDTO(endereco));
            }
        }
    }
}
