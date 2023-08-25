package br.com.fiap.grupo44.entrega.adpter.apiDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class IdadeDTO {

     private String date;
     private Integer age;
}
