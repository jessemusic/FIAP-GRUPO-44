package br.com.fiap.grupo44.entrega.repository;

import br.com.fiap.grupo44.entrega.entities.Eletrodomestico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IEletrodomesticoRepository  extends JpaRepository<Eletrodomestico, Long> {
}
