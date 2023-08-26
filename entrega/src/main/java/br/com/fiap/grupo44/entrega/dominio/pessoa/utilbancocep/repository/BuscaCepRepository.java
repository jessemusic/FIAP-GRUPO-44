package br.com.fiap.grupo44.entrega.dominio.pessoa.utilbancocep.repository;

import br.com.fiap.grupo44.entrega.dominio.pessoa.utilbancocep.entities.CriaCepAutomatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuscaCepRepository extends JpaRepository<CriaCepAutomatico,Long> {
}
