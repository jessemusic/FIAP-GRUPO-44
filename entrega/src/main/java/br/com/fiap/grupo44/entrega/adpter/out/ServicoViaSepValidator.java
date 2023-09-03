package br.com.fiap.grupo44.entrega.adpter.out;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.grupo44.entrega.adpter.apiDTO.CepDTO;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.EnderecoResultViaCepDTO;

@Service
public class ServicoViaSepValidator {

	@Value("${api-comunicationcep.host}")
	private String apiHostce;

	@Value("${api-comunicationcep.host.json}")
	private String json;
	private final RestTemplate restTemplate=new RestTemplate();

	public EnderecoResultViaCepDTO validarEndereco(CepDTO cepDTO) {
		return this.restTemplate.getForEntity(
				apiHostce+cepDTO.getCep()+json,
				EnderecoResultViaCepDTO.class).getBody();
     }

	public EnderecoResultViaCepDTO populaCep(String cepDTO) {
		return this.restTemplate.getForEntity(
				apiHostce+cepDTO+json,
				EnderecoResultViaCepDTO.class).getBody();
	}
}
