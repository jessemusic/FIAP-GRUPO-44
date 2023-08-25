package br.com.fiap.grupo44.entrega.dominio.pessoa.sevices;

import br.com.fiap.grupo44.entrega.adpter.apiDTO.ChamaResultDTO;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.ResultsDto;
import br.com.fiap.grupo44.entrega.adpter.out.RandomUseService;
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
    private RandomUseService criarRandomUseService;

    public Page<PessoaDTO> findAll(PageRequest pagina){
        var pessoas = repo.findAll(pagina);
        return pessoas.map(PessoaDTO::new);
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
        pessoaEntity.setIdade(pessoa.getIdade());
        pessoaEntity.setEmail(pessoa.getEmail());
        pessoaEntity.setPhone(pessoa.getPhone());
        pessoaEntity.setCell(pessoa.getCell());
        pessoaEntity.setFotosUrls(pessoa.getFotosUrls());
        pessoaEntity.setNat(pessoa.getNat());
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
        buscaPessoa.setIdade(pessoaDTO.getIdade());
        buscaPessoa.setEmail(pessoaDTO.getEmail());
        buscaPessoa.setPhone(pessoaDTO.getPhone());
        buscaPessoa.setCell(pessoaDTO.getCell());
        buscaPessoa.setFotosUrls(pessoaDTO.getFotosUrls());
        buscaPessoa.setNat(pessoaDTO.getNat());
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

    public Long deletePessoa(Long id) {
        Optional<Pessoa> existingPessoa = repo.findById(id);
        if (existingPessoa.isPresent()) {
            repo.deleteById(id);
        }else {
            throw  new ControllerNotFoundException("Pessoa não encontrada, impossível deletar se não existe pessoa no id: " + id);
        }
        return  repo.count();
    }

    public PessoaDTO insertAndCria() {
        ChamaResultDTO pessoaCriada = criarRandomUseService.getRandomUser();
        ResultsDto resultsDto = pessoaCriada.getResults().get(0);
        Pessoa pessoaEntity = new Pessoa();
        pessoaEntity.setNome(resultsDto.getName().getTitle() +" "+resultsDto.getName().getFirst() );
        pessoaEntity.setSobrenome(resultsDto.getName().getLast());
        pessoaEntity.setDataNascimento(resultsDto.getDob().getDate());
        pessoaEntity.setIdade(resultsDto.getDob().getAge());
        pessoaEntity.setSexo(resultsDto.getGender());
        pessoaEntity.setEmail(resultsDto.getEmail());
        pessoaEntity.setPhone(resultsDto.getPhone());
        pessoaEntity.setCell(resultsDto.getCell());
        pessoaEntity.setFotosUrls(resultsDto.getPicture().getLarge());
        pessoaEntity.setNat(resultsDto.getNat());
        var  pessoaSaved = repo.save(pessoaEntity);
        return new PessoaDTO(pessoaSaved);
    }
}
