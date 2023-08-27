package br.com.fiap.grupo44.entrega.dominio.eletrodomestico.repositories;

import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.entities.Eletrodomestico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IEletrodomesticoRepository  extends JpaRepository<Eletrodomestico, Long>, JpaSpecificationExecutor<Eletrodomestico> {
}
