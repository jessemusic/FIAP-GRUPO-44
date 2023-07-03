package br.com.fiap.grupo44.entrega.sevices;

import br.com.fiap.grupo44.entrega.dto.PessoaDTO;
import br.com.fiap.grupo44.entrega.dto.PessoaPatchDTO;
import br.com.fiap.grupo44.entrega.entities.Pessoa;
import br.com.fiap.grupo44.entrega.exception.ControllerNotFoundException;
import br.com.fiap.grupo44.entrega.repositories.IPessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

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

    public PessoaDTO insert(PessoaDTO pessoa){
        Pessoa pessoaEntity = new Pessoa();
        pessoaEntity.setNome(pessoa.getNome());
        pessoaEntity.setSobrenome(pessoa.getSobrenome());
        pessoaEntity.setDataNascimento(pessoa.getDataNascimento());
        pessoaEntity.setSexo(pessoa.getSexo());
        var  pessoaSaved = repo.save(pessoaEntity);
        return new PessoaDTO(pessoaSaved);
    }

    public PessoaDTO update(Long id,PessoaDTO pessoaDTO){
    try {
        Pessoa buscaPessoa = repo.getOne(id);
        buscaPessoa.setNome(pessoaDTO.getNome());
        buscaPessoa.setSobrenome(pessoaDTO.getSobrenome());
        buscaPessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        buscaPessoa.setSexo(pessoaDTO.getSexo());
        buscaPessoa = repo.save(buscaPessoa);
        return new PessoaDTO(buscaPessoa);
    }catch (EntityNotFoundException ene){
        throw new ControllerNotFoundException("Pessoa não encontrada, id: " + id);
    }

    }

    public PessoaPatchDTO updatePessoaByFields(Long id, Map<String, Object> fields) {
        Pessoa existingPessoa = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Pessoa não encontrada"));
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Pessoa.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingPessoa, value);
            });
            var pessoaAtualizada = repo.save(existingPessoa);
            return new PessoaPatchDTO(pessoaAtualizada);
    }
}

