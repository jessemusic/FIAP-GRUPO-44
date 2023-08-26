package br.com.fiap.grupo44.entrega.dominio.pessoa.utilbancocep.service;

import br.com.fiap.grupo44.entrega.dominio.pessoa.utilbancocep.entities.CriaCepAutomatico;
import br.com.fiap.grupo44.entrega.dominio.pessoa.utilbancocep.repository.BuscaCepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BuscaCepService {


    @Autowired
    private BuscaCepRepository repo;

    @Transactional(readOnly = true)
    public CriaCepAutomatico findById(Long idLogradourp){
        Optional<CriaCepAutomatico> cepAutomatico = repo.findById(idLogradourp);
          return cepAutomatico.orElse(null);
    }
}
