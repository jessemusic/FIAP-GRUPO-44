
package br.com.fiap.grupo44.entrega.dominio.pessoa.sevices;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.ChamaResultDTO;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.ResultsDto;
import br.com.fiap.grupo44.entrega.adpter.out.RandomUseService;


import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.dto.EletrodomesticoDTO;
import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.entities.Eletrodomestico;
import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.repositories.IEletrodomesticoRepository;
import br.com.fiap.grupo44.entrega.dominio.pessoa.dto.PessoaDTO;
import br.com.fiap.grupo44.entrega.dominio.pessoa.dto.PessoaPatchDTO;
import br.com.fiap.grupo44.entrega.dominio.pessoa.entities.Pessoa;
import br.com.fiap.grupo44.entrega.exception.ControllerNotFoundException;
import br.com.fiap.grupo44.entrega.dominio.pessoa.repositories.IPessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private IPessoaRepository repo;

    @Autowired
    private IEletrodomesticoRepository repoEletro;

    @Autowired
    private RandomUseService criarRandomUseService;

    public Page<PessoaDTO> findAll(PageRequest pagina){
        var pessoas = repo.findAll(pagina);
        return pessoas.map(PessoaDTO::new);
    }

    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        var pessoa = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Pessoa não encontrada"));
        return new PessoaDTO(pessoa, pessoa.getEletrodomesticos());
    }

    @Transactional
    public PessoaDTO insert(PessoaDTO dto) {
        Pessoa entity = new Pessoa();
        mapperDtoToEntity(dto,entity);
        var  pessoaSaved = repo.save(entity);
        return new PessoaDTO(pessoaSaved,pessoaSaved.getEletrodomesticos());
    }

    public PessoaDTO update(Long id,PessoaDTO dto){
        try {
            Pessoa entity = repo.getOne(id);
            mapperDtoToEntity(dto,entity);
            entity = repo.save(entity);
            return new PessoaDTO(entity);
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
        } else {
            throw new ControllerNotFoundException("Pessoa não encontrada, impossível deletar se não existe pessoa no id: " + id);
        }
        return repo.count();
    }

    public PessoaDTO insertAndCria() {
        ChamaResultDTO pessoaCriada = criarRandomUseService.getRandomUser();
        ResultsDto resultsDto = pessoaCriada.getResults().get(0);
        Pessoa entity = new Pessoa();
        entity.setNome(resultsDto.getName().getTitle() +" "+resultsDto.getName().getFirst() );
        entity.setSobrenome(resultsDto.getName().getLast());
        entity.setDataNascimento(resultsDto.getDob().getDate());
        entity.setIdade(resultsDto.getDob().getAge());
        entity.setSexo(resultsDto.getGender());
        entity.setEmail(resultsDto.getEmail());
        entity.setPhone(resultsDto.getPhone());
        entity.setCell(resultsDto.getCell());
        entity.setFotosUrls(resultsDto.getPicture().getLarge());
        entity.setNat(resultsDto.getNat());
        var  pessoaSaved = repo.save(entity);
        return new PessoaDTO(pessoaSaved);
    }

    private void mapperDtoToEntity(PessoaDTO dto, Pessoa entity) {
        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setSexo(dto.getSexo());
        entity.setIdade(dto.getIdade());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setCell(dto.getCell());
        entity.setFotosUrls(dto.getFotosUrls());
        entity.setNat(dto.getNat());

        for (EletrodomesticoDTO eletrodomesticoDTO: dto.getEletrodomesticos()) {
            Eletrodomestico eletrodomestico = repoEletro.getOne(eletrodomesticoDTO.getId());
            entity.getEletrodomesticos().add(eletrodomestico);
        }
    }
}
