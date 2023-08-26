package br.com.fiap.grupo44.entrega.dominio.endereco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.grupo44.entrega.dominio.endereco.entities.Endereco;

public interface IEEnderecoRepository extends JpaRepository<Endereco,Long> {
}
