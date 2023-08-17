package br.com.fiap.grupo44.entrega.sevices;

import br.com.fiap.grupo44.entrega.dto.EletrodomesticoDTO;
import br.com.fiap.grupo44.entrega.dto.PessoaDTO;
import br.com.fiap.grupo44.entrega.dto.PessoaPatchDTO;
import br.com.fiap.grupo44.entrega.entities.Eletrodomestico;
import br.com.fiap.grupo44.entrega.entities.Pessoa;
import br.com.fiap.grupo44.entrega.exception.ControllerNotFoundException;
import br.com.fiap.grupo44.entrega.repositories.IEletrodomesticoRepository;
import br.com.fiap.grupo44.entrega.repositories.IPessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private IPessoaRepository repo;

    @Autowired
    private IEletrodomesticoRepository eletroRepository;

    @Transactional(readOnly = true)
    public Page<PessoaDTO> findAll(PageRequest pagina){
        var pessoas = repo.findAll(pagina);
        return pessoas.map(pessoa -> new PessoaDTO(pessoa,pessoa.getEletrodomesticoSet()));
    }
    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id){
        var pessoa = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Pessoa não encontrada"));
        return new PessoaDTO(pessoa,pessoa.getEletrodomesticoSet());
    }
    @Transactional
    public PessoaDTO insert(PessoaDTO pessoa){
        Pessoa pessoaEntity = new Pessoa();
        mapperDtoToEntity(pessoa,pessoaEntity);
        var  pessoaSaved = repo.save(pessoaEntity);
        return new PessoaDTO(pessoaSaved);
    }

    public PessoaDTO update(Long id,PessoaDTO pessoaDTO){
    try {
        Pessoa buscaPessoa = repo.getOne(id);
        mapperDtoToEntity(pessoaDTO,buscaPessoa);
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

    public Long deletePessoa(Long id) {
        Optional<Pessoa> existingPessoa = repo.findById(id);
        if (existingPessoa.isPresent()) {
            repo.deleteById(id);
        }else {
            throw  new ControllerNotFoundException("Pessoa não encontrada, impossível deletar se não existe pessoa no id: " + id);
        }
        return  repo.count();
    }

    private void mapperDtoToEntity(PessoaDTO dto, Pessoa entity){
        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setSexo(dto.getSexo());
        entity = repo.save(entity);

        for (EletrodomesticoDTO eletroDTO: dto.getEletrodomesticos()) {
            Eletrodomestico eletrodomestico = eletroRepository.getOne(eletroDTO.getId());
            entity.getEletrodomesticoSet().add(eletrodomestico);
        }
    }
}

