package br.com.fiap.grupo44.entrega.dominio.eletrodomestico.entities;
import jakarta.persistence.*;
import lombok.*;

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
    private double consumo;

}