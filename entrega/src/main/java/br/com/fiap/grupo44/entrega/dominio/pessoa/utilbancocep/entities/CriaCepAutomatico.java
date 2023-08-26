package br.com.fiap.grupo44.entrega.dominio.pessoa.utilbancocep.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "logradouro")
public class CriaCepAutomatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logradouro")
    private Long idLogradourp;

    private String cep;

    public Long getIdLogradourp() {
        return idLogradourp;
    }

    public void setIdLogradourp(Long idLogradourp) {
        this.idLogradourp = idLogradourp;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
