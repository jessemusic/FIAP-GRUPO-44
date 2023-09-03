package br.com.fiap.grupo44.entrega.adpter.apiDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CepDTO {
	@NotBlank(message = "O campo CEP é obrigatório seu preenchimento.")
	private String cep;
}
