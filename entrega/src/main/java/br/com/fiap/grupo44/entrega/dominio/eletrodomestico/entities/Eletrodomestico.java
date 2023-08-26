package br.com.fiap.grupo44.entrega.dominio.eletrodomestico.entities;
import br.com.fiap.grupo44.entrega.dominio.pessoa.entities.Pessoa;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "tb_eletrodomestico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Eletrodomestico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String modelo;
    private String marca;
    private String tensao;
    private double potencia;
    private double consumoDiario;
    private double consumoMensal;
    private Long usoDiasEstimados;
    private Long usoDiarioEstimado;
    @ManyToMany(mappedBy = "eletrodomesticos")
    private Set<Pessoa> pessoas;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataDeCriacao;

}
