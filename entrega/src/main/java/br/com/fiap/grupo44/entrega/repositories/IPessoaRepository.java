package br.com.fiap.grupo44.entrega.repositories;

import br.com.fiap.grupo44.entrega.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPessoaRepository extends JpaRepository<Pessoa,Long> {
}
