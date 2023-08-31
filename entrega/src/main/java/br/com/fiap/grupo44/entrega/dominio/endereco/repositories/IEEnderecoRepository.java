package br.com.fiap.grupo44.entrega.dominio.endereco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.grupo44.entrega.dominio.endereco.entities.Endereco;

public interface IEEnderecoRepository extends JpaRepository<Endereco,Long> {
	@Query(value = "SELECT * FROM TB_ENDERECO E WHERE E.CEP=?1",nativeQuery = true)
	public Endereco BUSCAR_ENDERECO_POR_CEP(String cep); 
	@Transactional
    @Modifying
	@Query(value = "INSERT INTO TB_ENDERECO_PESSOA(ENDERECO_ID,PESSOA_ID) VALUES(?1,?2)",nativeQuery = true)
	int SALVAR_ENDERECO(String endereco,Long pessoa);
}
