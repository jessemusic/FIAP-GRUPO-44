package br.com.fiap.grupo44.entrega.adpter.out;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.grupo44.entrega.adpter.apiDTO.CepDTO;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.EnderecoResultViaCepDTO;

@Service
public class ServicoViaSepValidator {
	private RestTemplate restTemplate=new RestTemplate();

	public EnderecoResultViaCepDTO validarEndereco(CepDTO cepDTO) {
		EnderecoResultViaCepDTO enderecoViaCepDTO = this.restTemplate.getForEntity("https://viacep.com.br/ws/"+cepDTO.getCep()+"/json/", EnderecoResultViaCepDTO.class).getBody();
		return enderecoViaCepDTO;
     }
}
