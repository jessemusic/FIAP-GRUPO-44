package br.com.fiap.grupo44.entrega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.grupo44.entrega.dto.EnderecoDTO;
import br.com.fiap.grupo44.entrega.entities.Endereco;
import br.com.fiap.grupo44.entrega.exception.ControllerNotFoundException;
import br.com.fiap.grupo44.entrega.repository.IEEnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private IEEnderecoRepository enderecoRepository;

	public EnderecoDTO findById(Long id) {
		Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Endereço não encontrado"));
		return new EnderecoDTO(endereco);
	}
	
	public Page<EnderecoDTO> findAll(PageRequest pagina){
        var enderecos = enderecoRepository.findAll(pagina);
        return enderecos.map(endereco -> new EnderecoDTO(endereco));
    }
}
