package br.com.fiap.grupo44.entrega.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "tb_eletrodomestico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Eletrodomestico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String modelo;
    private String voltagem;
    private String potencia;
    private Double consumo;

}
