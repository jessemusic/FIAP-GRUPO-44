package br.com.fiap.grupo44.entrega.dto;

import java.util.UUID;

import br.com.fiap.grupo44.entrega.entities.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
	private UUID id;
	private String rua;
	private Integer numero; 
	private String bairro;
	private String cidade;
	private String estado;
	
	public EnderecoDTO(Endereco endereco) {
		this.id     = endereco.getId();
		this.rua    = endereco.getRua();
		this.numero = endereco.getNumero();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
	}
}
