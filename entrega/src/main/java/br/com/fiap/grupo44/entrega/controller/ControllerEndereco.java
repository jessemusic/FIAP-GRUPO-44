package br.com.fiap.grupo44.entrega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.grupo44.entrega.dto.EnderecoDTO;
import br.com.fiap.grupo44.entrega.service.EnderecoService;

@RestController
public class ControllerEndereco {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping("/salvar")
	public EnderecoDTO salvar(@RequestBody EnderecoDTO enderecoDTO) {
		enderecoDTO = this.enderecoService.salvar(enderecoDTO);
		return enderecoDTO;
	}
	
	@GetMapping("/buscar-todos")
	public List<EnderecoDTO> getAll() {
		return this.enderecoService.findAll();
	}
	
	@GetMapping("/buscar-getID")
	public EnderecoDTO getID(@RequestParam Long id) {
		return this.enderecoService.findById(id);
	}
}
