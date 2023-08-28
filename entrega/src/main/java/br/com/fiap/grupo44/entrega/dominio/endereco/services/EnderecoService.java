package br.com.fiap.grupo44.entrega.dominio.endereco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.grupo44.entrega.dominio.endereco.dto.EnderecoDTO;
import br.com.fiap.grupo44.entrega.dominio.endereco.dto.EnderecoViaCepDTO;
import br.com.fiap.grupo44.entrega.dominio.endereco.entities.Endereco;
import br.com.fiap.grupo44.entrega.exception.ControllerNotFoundException;
import br.com.fiap.grupo44.entrega.dominio.endereco.repositories.IEEnderecoRepository;

@Service
public class EnderecoService {
 
	@Autowired
	private IEEnderecoRepository enderecoRepository;
	private RestTemplate restTemplate=new RestTemplate();


	public EnderecoDTO salvar(EnderecoDTO enderecoDTO,String cep) {
		
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
		
		Endereco endereco = this.enderecoRepository.save(enderecoDTO.getEndereco(enderecoDTO));
		
		return new EnderecoDTO(endereco);
	}
	
	public EnderecoDTO findById(Long id) {
		Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Endereço não encontrado"));
		return new EnderecoDTO(endereco);
	}

	public List<EnderecoDTO> findAll(PageRequest pageRequest){
        var enderecos = enderecoRepository.findAll(pageRequest);
        if(!enderecos.isEmpty()) {
        	return enderecos.getContent().stream().map(endereco -> new EnderecoDTO(endereco)).toList();        	
        }
        throw new ControllerNotFoundException("Nenhum Endereço para listar na pagina especificada.");
    }

	public EnderecoDTO atualizar(EnderecoDTO enderecoDTO,Long id) {
        Optional<Endereco> OPendereco = this.enderecoRepository.findById(id);
        try {
        	Endereco endereco = OPendereco.get();
        	endereco.setBairro(enderecoDTO.getBairro());
        	endereco.setCidade(enderecoDTO.getCidade());
        	endereco.setEstado(enderecoDTO.getEstado());
        	endereco.setNumero(enderecoDTO.getNumero());
        	endereco.setRua(enderecoDTO.getRua());
        	this.enderecoRepository.save(endereco);
        	return new EnderecoDTO(endereco);
        }catch (Exception e) {
            throw new ControllerNotFoundException("Endereço não encontrado, id: " + id);
		}
	}

	public String apagar(Long id) {
		Optional<Endereco> OPendereco = this.enderecoRepository.findById(id);
		if(OPendereco.isPresent()){
			Endereco endereco = OPendereco.get();
			this.enderecoRepository.delete(endereco);
			return "Removido o endereço de ID: "+id;
		}
        throw new ControllerNotFoundException("Endereço não encontrado, id: " + id);
	}
}
