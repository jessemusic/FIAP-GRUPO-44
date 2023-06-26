package br.com.fiap.grupo44.entrega.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.grupo44.entrega.dto.EnderecoDTO;
import br.com.fiap.grupo44.entrega.entities.Endereco;
import br.com.fiap.grupo44.entrega.exception.ControllerNotFoundException;
import br.com.fiap.grupo44.entrega.repository.IEEnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private IEEnderecoRepository enderecoRepository;

	public EnderecoDTO salvar(EnderecoDTO enderecoDTO) {
		Endereco endereco = this.enderecoRepository.save(enderecoDTO.getEndereco(enderecoDTO));
		return new EnderecoDTO(endereco);
	}
	
	public EnderecoDTO findById(Long id) {
		Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Endereço não encontrado"));
		return new EnderecoDTO(endereco);
	}

	public List<EnderecoDTO> findAll(){
        var enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(endereco -> new EnderecoDTO(endereco)).toList();
    }
}
