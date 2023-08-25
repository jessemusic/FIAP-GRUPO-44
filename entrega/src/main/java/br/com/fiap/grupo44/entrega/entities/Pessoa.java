package br.com.fiap.grupo44.entrega.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Character sexo;
    private String dataNascimento;
    private Integer age;
    private String email;
    private String phone;
    private String cell;
    private String fotosUrls;
    private String nat;

}
