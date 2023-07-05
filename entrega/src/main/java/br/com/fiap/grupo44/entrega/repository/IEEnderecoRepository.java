package br.com.fiap.grupo44.entrega.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.grupo44.entrega.entities.Endereco;

public interface IEEnderecoRepository extends JpaRepository<Endereco,Long> {
}
