package br.com.fiap.grupo44.entrega.dominio.pessoa.entities;


import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.entities.Eletrodomestico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String sexo;
    private String dataNascimento;
    private Integer idade;
    private String email;
    private String phone;
    private String cell;
    private String fotosUrls;
    private String nat;
    private Double somatorioCustoMensal;
    @CreationTimestamp
    private Instant dataDeCriacao;
    @ManyToMany( fetch =  FetchType.LAZY)
        @JoinTable(
                name = "tb_pessoa_eletrodomestico",
                joinColumns = @JoinColumn(name = "pessoa_id"),
                inverseJoinColumns = @JoinColumn(name = "eletrodomestico_id")
        )
    private Set<Eletrodomestico> eletrodomesticos = new HashSet<>();


}
