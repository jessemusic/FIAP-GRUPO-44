package br.com.fiap.grupo44.entrega.dto;

import br.com.fiap.grupo44.entrega.entities.Eletrodomestico;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class EletrodomesticoDTO {
    @JsonProperty
    private UUID id;

    @JsonProperty
    @NotNull(message = "Nome não deve ser nulo")
    @NotBlank(message = "Nome não deve ser vazio")
    private String nome;

    @JsonProperty
    @NotNull(message = "Modelo não deve ser nulo")
    @NotBlank(message = " não deve ser vazio")
    private String modelo;

    //110v,220v, Bivolt
    @JsonProperty
    @NotNull(message = "É necessario inserir a voltagem de seu eletrodomestico")
    @NotBlank(message = "Voltagem do  eletrodomestico não pode ser vazio")
    private String voltagem;

    @JsonProperty
    @NotNull(message = "É necessario inserir a Potencia de seu eletrodomestico")
    @NotBlank(message = "Potencia do  eletrodomestico não pode ser vazio")
    private String potencia;

    @JsonProperty
    private Double consumo;

    public EletrodomesticoDTO(Eletrodomestico entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.modelo = entidade.getModelo();
        this.voltagem = entidade.getVoltagem();
        this.potencia = entidade.getPotencia();
    }

}
