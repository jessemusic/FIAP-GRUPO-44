package br.com.fiap.grupo44.entrega.adpter.apiDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResultsDto {

    private String gender;
    private NameDTO name;
    private String email;
    private IdadeDTO dob;
    private String phone;
    private String cell;
    private FotoPessoalDTO picture;
    private String nat;

}
