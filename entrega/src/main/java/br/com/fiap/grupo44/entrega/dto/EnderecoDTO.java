package br.com.fiap.grupo44.entrega.dto;

import br.com.fiap.grupo44.entrega.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
	
	private Long id;
	private String rua;
	private Integer numero; 
	private String bairro;
	private String cidade;
	private String estado;
	
	public EnderecoDTO() {
		
	}
	
	public EnderecoDTO(Endereco endereco) {
		this.id     = endereco.getId();
		this.rua    = endereco.getRua();
		this.numero = endereco.getNumero();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
	}
	
	public Endereco getEndereco(EnderecoDTO enderecoDTO) {
		System.err.println("MONTEI O OBJECTO ENDEREÇO.");
		Endereco endereco=new Endereco();
		endereco.setId(enderecoDTO.getId());
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setCidade(enderecoDTO.getCidade());
		endereco.setEstado(enderecoDTO.getEstado());
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setRua(enderecoDTO.getRua());
		
		return endereco;
	}
}
