package br.com.fiap.grupo44.entrega.sevices;

import br.com.fiap.grupo44.entrega.dto.PessoaDTO;
import br.com.fiap.grupo44.entrega.exception.ControllerNotFoundException;
import br.com.fiap.grupo44.entrega.repositories.IPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private IPessoaRepository repo;

    public Page<PessoaDTO> findAll(PageRequest pagina){
        var pessoas = repo.findAll(pagina);
        return pessoas.map(pessoa -> new PessoaDTO(pessoa));
    }

    public PessoaDTO findById(Long id){
        var pessoa = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Pessoa não encontrada"));
        return new PessoaDTO(pessoa);
    }
}
