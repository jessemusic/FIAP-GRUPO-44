package br.com.fiap.grupo44.entrega.dto.apiDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChamaResultDTO {

   ArrayList<ResultsDto> results = new ArrayList<>();

}
