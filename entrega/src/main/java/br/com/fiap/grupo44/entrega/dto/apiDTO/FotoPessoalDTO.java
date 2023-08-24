package br.com.fiap.grupo44.entrega.dto.apiDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FotoPessoalDTO {

    private String large;
    private String medium;
    private String thumbnail;
}
