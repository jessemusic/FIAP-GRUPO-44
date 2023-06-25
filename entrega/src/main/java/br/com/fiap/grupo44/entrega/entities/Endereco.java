package br.com.fiap.grupo44.entrega.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_endereco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Endereco {
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
	private String rua;
	private Integer numero; 
	private String bairro;
	private String cidade;
	private String estado;
}
