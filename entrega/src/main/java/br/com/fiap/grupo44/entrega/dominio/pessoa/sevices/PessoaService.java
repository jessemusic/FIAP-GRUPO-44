
package br.com.fiap.grupo44.entrega.dominio.pessoa.sevices;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.CepDTO;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.ChamaResultDTO;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.EnderecoResultViaCepDTO;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.ResultsDto;
import br.com.fiap.grupo44.entrega.adpter.out.RandomUseService;


import br.com.fiap.grupo44.entrega.adpter.out.ServicoViaSepValidator;
import br.com.fiap.grupo44.entrega.dominio.endereco.dto.EnderecoDTO;
import br.com.fiap.grupo44.entrega.dominio.endereco.entities.Endereco;
import br.com.fiap.grupo44.entrega.dominio.endereco.repositories.IEEnderecoRepository;
import br.com.fiap.grupo44.entrega.dominio.endereco.services.EnderecoService;
import br.com.fiap.grupo44.entrega.dominio.pessoa.dto.PessoaDTO;
import br.com.fiap.grupo44.entrega.dominio.pessoa.dto.PessoaPatchDTO;
import br.com.fiap.grupo44.entrega.dominio.pessoa.entities.Pessoa;
import br.com.fiap.grupo44.entrega.dominio.pessoa.utilbancocep.entities.CriaCepAutomatico;
import br.com.fiap.grupo44.entrega.dominio.pessoa.utilbancocep.service.BuscaCepService;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private IPessoaRepository repo;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private BuscaCepService buscaCepService;

    @Autowired
    private RandomUseService criarRandomUseService;

    @Autowired
    private IEEnderecoRepository enderecoRepository;
    @Autowired
    private ServicoViaSepValidator servicoViaSepValidator;

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

    @Transactional
    public PessoaDTO update(Long id,PessoaDTO dto){
        try {
            Pessoa entity = repo.getOne(id);
            mapperDtoToEntity(dto,entity);
            entity = repo.save(entity);

            return new PessoaDTO(entity,entity.getEletrodomesticos());
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
        insertAndCriaEndereço(pessoaEntity);
        var  pessoaSaved = repo.save(pessoaEntity);
        return new PessoaDTO(pessoaSaved);
    }

    public Pessoa insertAndCriaEndereço(Pessoa pessoa) {
        Long idDoCep = (long)(Math.random()*1019411) +1;
        int numeroDaCasaCriado = (int)(Math.random()*1000) +1;
        CriaCepAutomatico criaCepAutomatico = buscaCepService.findById(idDoCep);
        CepDTO cepEnviar = new CepDTO();
        cepEnviar.setCep(criaCepAutomatico.getCep());
        EnderecoResultViaCepDTO enderecoResultViaCepDTO = this.servicoViaSepValidator.validarEndereco(cepEnviar);
        final Endereco enderecoEntity = enderecoResultViaCepDTO.getEndereco();
        enderecoEntity.setNumero(numeroDaCasaCriado);
        var enderecoSaved = this.enderecoRepository.save(enderecoEntity);
        pessoa.setEndereco(enderecoSaved);
        return  pessoa;
    }

}
