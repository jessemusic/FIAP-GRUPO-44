package br.com.fiap.grupo44.entrega.dominio.endereco.entities;

import java.util.List;

import br.com.fiap.grupo44.entrega.dominio.pessoa.entities.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
	private String cep;
	private String rua;
	private Integer numero; 
	private String bairro;
	private String cidade;
	private String estado;
	@ManyToMany
	@JoinTable(name = "tb_endereco_pessoa",joinColumns = @JoinColumn(name = "endereco_id"),inverseJoinColumns = @JoinColumn(name = "pessoa_id"))
	private List<Pessoa> pessoas;
}