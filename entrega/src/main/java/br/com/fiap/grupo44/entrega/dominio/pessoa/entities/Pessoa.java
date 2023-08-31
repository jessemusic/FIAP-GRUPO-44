package br.com.fiap.grupo44.entrega.dominio.pessoa.entities;


import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.entities.Eletrodomestico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String sexo;
    private String dataNascimento;
    private Integer idade;
    private String email;
    private String phone;
    private String cell;
    private String fotosUrls;
    private String nat;
    @CreationTimestamp
    private Instant dataDeCriacao;
    @ManyToMany( fetch =  FetchType.LAZY)
        @JoinTable(
                name = "tb_pessoa_eletrodomestico",
                joinColumns = @JoinColumn(name = "pessoa_id"),
                inverseJoinColumns = @JoinColumn(name = "eletrodomestico_id")
        )
    private Set<Eletrodomestico> eletrodomesticos = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getFotosUrls() {
        return fotosUrls;
    }

    public void setFotosUrls(String fotosUrls) {
        this.fotosUrls = fotosUrls;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public Instant getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Instant dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Set<Eletrodomestico> getEletrodomesticos() {
        return eletrodomesticos;
    }

    public void setEletrodomesticos(Set<Eletrodomestico> eletrodomesticos) {
        this.eletrodomesticos = eletrodomesticos;
    }
}
