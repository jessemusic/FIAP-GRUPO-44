package br.com.fiap.grupo44.entrega.adpter.apiDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NameDTO {
    private String title;
    private String first;
    private String last;
}
