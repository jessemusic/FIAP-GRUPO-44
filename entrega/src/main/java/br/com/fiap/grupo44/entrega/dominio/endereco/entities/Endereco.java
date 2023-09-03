package br.com.fiap.grupo44.entrega.dominio.endereco.entities;

import br.com.fiap.grupo44.entrega.dominio.pessoa.entities.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_endereco")
public class Endereco {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String rua;
	private Integer numero; 
	private String bairro;
	private String cidade;
	private String estado;



	@OneToMany(mappedBy = "endereco")
	private Set<Pessoa> pessoa = new HashSet<>();

}