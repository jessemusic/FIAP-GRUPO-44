package br.com.fiap.grupo44.entrega.dominio.pessoa.repositories;

import br.com.fiap.grupo44.entrega.dominio.pessoa.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa,Long> {
}
