package br.com.fiap.grupo44.entrega.dominio.endereco.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoViaCepDTO {
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private Integer ibge;
	private Integer gia;
	private Integer ddd;
	private Integer siafi;
	@Override
	public String toString() {
		return "EnderecoViaCep [cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf + ", ibge=" + ibge + ", gia=" + gia
				+ ", ddd=" + ddd + ", siafi=" + siafi + "]";
	}
}
