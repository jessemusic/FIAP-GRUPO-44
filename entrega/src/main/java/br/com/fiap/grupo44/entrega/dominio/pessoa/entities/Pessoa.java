package br.com.fiap.grupo44.entrega.dominio.pessoa.entities;


import br.com.fiap.grupo44.entrega.dominio.endereco.entities.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
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

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;
}
