package br.com.fiap.grupo44.entrega.dominio.eletrodomestico.dto;

import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.entities.Eletrodomestico;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EletrodomesticoDTO {
    @JsonProperty
    private Long id;

    @JsonProperty
    @NotNull(message = "Nome não deve ser nulo")
    @NotBlank(message = "Nome não deve ser vazio")
    private String nome;

    @JsonProperty
    @NotNull(message = "Modelo não deve ser nulo")
    @NotBlank(message = "Modelo não deve ser vazio")
    private String modelo;

    @JsonProperty
    @NotNull(message = "Marca não deve ser nulo")
    @NotBlank(message = "Marca não deve ser vazio")
    private String marca;

    //110v,220v, Bivolt
    @JsonProperty
    @NotNull(message = "É necessario inserir a voltagem de seu eletrodomestico")
    @NotBlank(message = "Voltagem do  eletrodomestico não pode ser vazio")
    private String tensao;

    @JsonProperty
    @NotNull(message = "É necessario inserir a tensão de seu eletrodomestico")
    @Min(value = 1, message = "A tensão precisa ser de no minimo 1W")
    private Double potencia;

    @JsonProperty
    @NotNull(message = "É necessario inserir o numero de dias de uso estimados")
    @Min(value = 1, message = "É necessario usar ao menos 1 dia no mes")
    @Min(value = 31, message = "É necessario usar no maximo 31 dia no mes")
    private Long  usoDiasEstimados;

    @JsonProperty
    @NotNull(message = "É necessario inserir a estimativa de uso diario")
    @Min(value = 1440, message = "Limite de 1440 equivalente a 24 horas")
    private Long  usoDiarioEstimado;

    @JsonProperty
    @Null(message = "O valor inicial do consumo diario deve ser nulo")
    private Double consumoDiario;

    @JsonProperty
    @Null(message = "O valor inicial do consumo mensal deve ser nulo")
    private Double consumoMensal;

    @JsonProperty
    @Null(message = "O valor inicial do custo diario deve ser nulo")
    private double custoDiario;
    @JsonProperty
    @Null(message = "O valor inicial do custo mensal deve ser nulo")
    private double custoMensal;

    @JsonProperty
    private Long idPatchCategoria;

    public EletrodomesticoDTO(Eletrodomestico entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.modelo = entidade.getModelo();
        this.marca  = entidade.getMarca();
        this.tensao = entidade.getTensao();
        this.potencia = entidade.getPotencia();
        this.usoDiasEstimados = entidade.getUsoDiasEstimados();
        this.usoDiarioEstimado = entidade.getUsoDiarioEstimado();
        this.consumoDiario = entidade.getConsumoDiario();
        this.consumoMensal = entidade.getConsumoMensal();
        this.custoDiario = entidade.getCustoDiario();
        this.custoMensal = entidade.getCustoMensal();
        this.idPatchCategoria = entidade.getIdPatchCategoria();
    }
}
