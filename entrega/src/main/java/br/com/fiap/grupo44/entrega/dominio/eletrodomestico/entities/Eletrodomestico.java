package br.com.fiap.grupo44.entrega.dominio.eletrodomestico.entities;
import br.com.fiap.grupo44.entrega.dominio.pessoa.entities.Pessoa;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
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
    private Double potencia;
    private Double consumoDiario;
    private Double consumoMensal;
    private Double custoDiario;
    private Double custoMensal;
    private Long usoDiasEstimados;
    private Long usoDiarioEstimado;
    private Long idPatchCategoria;
    @ManyToMany(mappedBy = "eletrodomesticos")
    private Set<Pessoa> pessoas;
    @CreationTimestamp
    private Instant dataDeCriacao;

}
