package br.com.fiap.grupo44.entrega.adpter.apiDTO;

import br.com.fiap.grupo44.entrega.dominio.pessoa.entities.Pessoa;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CepDTO {
	@NotBlank(message = "O campo CEP é obrigatório seu preenchimento.")
	private String cep;
	private Pessoa pessoa;
}
