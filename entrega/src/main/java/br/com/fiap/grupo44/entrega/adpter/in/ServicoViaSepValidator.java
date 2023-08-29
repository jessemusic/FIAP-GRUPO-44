package br.com.fiap.grupo44.entrega.adpter.in;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.grupo44.entrega.adpter.apiDTO.EnderecoViaCepDTO;
import br.com.fiap.grupo44.entrega.dominio.endereco.dto.EnderecoDTO;
import br.com.fiap.grupo44.entrega.exception.ControllerNotFoundException;

@Service
public class ServicoViaSepValidator {
	private RestTemplate restTemplate=new RestTemplate();

	public void validarEndereco(EnderecoDTO enderecoDTO,String cep) {
    	 if(cep!=null) {
 			EnderecoViaCepDTO enderecoViaCepDTO = this.restTemplate.getForEntity("https://viacep.com.br/ws/"+cep+"/json/", EnderecoViaCepDTO.class).getBody();
 			
 			if(!enderecoDTO.getEstado().equals(enderecoViaCepDTO.getUf())) 
 				throw new ControllerNotFoundException("O estado informado não é válido.");	
 			if(!enderecoDTO.getCidade().equals(enderecoViaCepDTO.getLocalidade())) 
 				throw new ControllerNotFoundException("A cidade informada não é válida.");				
 			if(!enderecoDTO.getBairro().equals(enderecoViaCepDTO.getBairro())) 
 				throw new ControllerNotFoundException("O Bairro informado não é válido.");	
 			System.err.println(enderecoViaCepDTO);			
 		}
     }
}
