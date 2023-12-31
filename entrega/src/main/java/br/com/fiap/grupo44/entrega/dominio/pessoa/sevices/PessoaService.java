
package br.com.fiap.grupo44.entrega.dominio.pessoa.sevices;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.CepDTO;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.ChamaResultDTO;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.EnderecoResultViaCepDTO;
import br.com.fiap.grupo44.entrega.adpter.apiDTO.ResultsDto;
import br.com.fiap.grupo44.entrega.adpter.out.RandomUseService;


import br.com.fiap.grupo44.entrega.adpter.out.ServicoViaSepValidator;
import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.dto.EletrodomesticoDTO;
import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.entities.Eletrodomestico;
import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.repositories.IEletrodomesticoRepository;
import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.services.EletrodomesticoService;
import br.com.fiap.grupo44.entrega.dominio.endereco.dto.EnderecoDTO;
import br.com.fiap.grupo44.entrega.dominio.endereco.dto.Paginator;
import br.com.fiap.grupo44.entrega.dominio.endereco.dto.RestDataReturnDTO;
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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private IPessoaRepository repo;

    @Autowired
    private EletrodomesticoService eletrodomesticoService;


    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private IEletrodomesticoRepository repoEletro;

    @Autowired
    private BuscaCepService buscaCepService;

    @Autowired
    private RandomUseService criarRandomUseService;

    @Autowired
    private IEEnderecoRepository enderecoRepository;
    @Autowired
    private ServicoViaSepValidator servicoViaSepValidator;

    public RestDataReturnDTO findAll(PageRequest pagina){
    	List<PessoaDTO> PessoasDTO=new ArrayList<PessoaDTO>();
    	List<EnderecoDTO> enderecosDTO;
    	EnderecoDTO enderecoDTO;
        List<EletrodomesticoDTO> enderecosDTOList;
        EletrodomesticoDTO eletrodomesticoDTO;
    	
    	PessoaDTO pessoaDTO=null;
        ;
    	var pessoas = repo.findAll(pagina);
        final Page<PessoaDTO> map = pessoas.map(PessoaDTO::new);
        for (Pessoa pessoa : pessoas.getContent()) {
    		enderecosDTO= new ArrayList<EnderecoDTO>();
    		pessoaDTO   = new PessoaDTO(pessoa,pessoa.getEletrodomesticos());
            enderecosDTOList = new ArrayList<>();

          //PARSEAR DADOS DE PESSOA
			BeanUtils.copyProperties(pessoa, pessoaDTO);
			PessoasDTO.add(pessoaDTO);

            Double somatorioMensal = 0.0;
            if (pessoa.getEletrodomesticos() != null && !pessoa.getEletrodomesticos().isEmpty()) {
                somatorioMensal = pessoa.getEletrodomesticos().stream()
                        .mapToDouble(Eletrodomestico::getCustoMensal)
                        .sum();
            }
            pessoaDTO.setSomatorioCustoMensal(somatorioMensal);

            // Carregue os eletrodomésticos associados a esta pessoa
            List<EletrodomesticoDTO> eletrodomesticosDTO = pessoa.getEletrodomesticos().stream()
                    .map(EletrodomesticoDTO::new)
                    .collect(Collectors.toList());

            pessoaDTO.setEletrodomesticos(eletrodomesticosDTO);
    		
    		 for (Endereco endereco : pessoa.getEnderecos()) {
				//PARSSEAR DADOS DE ENDEREÇO
    			 enderecoDTO=new EnderecoDTO();
    			 BeanUtils.copyProperties(endereco, enderecoDTO);
    			 enderecosDTO.add(enderecoDTO);
			}
            final Set<Eletrodomestico> eletrodomesticos = pessoa.getEletrodomesticos();
            System.out.println(eletrodomesticos);
            for (Eletrodomestico eletrodomestico : pessoa.getEletrodomesticos()) {
                //PARSSEAR DADOS DE ELETRODOMÉSTICOS
                eletrodomesticoDTO=new EletrodomesticoDTO();
                BeanUtils.copyProperties(eletrodomestico, eletrodomesticoDTO);
                enderecosDTOList.add(eletrodomesticoDTO);
            }
             pessoaDTO.setEletrodomesticos(enderecosDTOList);
            pessoaDTO.setEnderecos(enderecosDTO);
		}

    	return new RestDataReturnDTO(PessoasDTO, new Paginator(pessoas.getNumber(), pessoas.getTotalElements(), pessoas.getTotalPages()));

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
        final Set<Eletrodomestico> eletrodomesticos = selecionarEletrodomesticosAleatoriamente();
        pessoaEntity.setEletrodomesticos(eletrodomesticos);
        var  pessoaSaved = repo.save(pessoaEntity);
        final EnderecoDTO enderecoDTO = insertAndCriaEndereco(pessoaSaved);

        return new PessoaDTO(pessoaSaved, eletrodomesticos,Arrays.asList(enderecoDTO));
    }

    public Set<Eletrodomestico> selecionarEletrodomesticosAleatoriamente() {
        Set<Eletrodomestico> eletrodomesticos = new HashSet<>(repoEletro.findAll());
        Map<Long, Set<Eletrodomestico>> eletrodomesticosPorCategoria = eletrodomesticos.stream()
                .collect(Collectors.groupingBy(Eletrodomestico::getIdPatchCategoria, Collectors.toSet()));
        Random random = new Random();
        List<Eletrodomestico> eletrodomesticosSelecionados = new ArrayList<>();
        eletrodomesticosPorCategoria.values().forEach(eletrodomesticosCategoria -> {
        List<Eletrodomestico> eletrodomesticosCategoriaLista = new ArrayList<>(eletrodomesticosCategoria);
            if (!eletrodomesticosCategoriaLista.isEmpty()) {
                Collections.shuffle(eletrodomesticosCategoriaLista);
                Eletrodomestico eletrodomesticoAleatorio = eletrodomesticosCategoriaLista.get(random.nextInt(eletrodomesticosCategoriaLista.size()));
                eletrodomesticosSelecionados.add(eletrodomesticoAleatorio);         }     });
        int quantidadeTotal = Math.min(random.nextInt(12) + 1, eletrodomesticosSelecionados.size());
        return eletrodomesticosSelecionados.stream() .limit(quantidadeTotal).collect(Collectors.toSet()); }



    public EnderecoDTO insertAndCriaEndereco(Pessoa pessoa) {
        Long idDoCep = (long)(Math.random()*1019411) +1;
        int numeroDaCasaCriado = (int)(Math.random()*1000) +1;
        CriaCepAutomatico criaCepAutomatico = buscaCepService.findById(idDoCep);
        
        CepDTO cepEnviar = new CepDTO();
        cepEnviar.setCep(criaCepAutomatico.getCep());
        cepEnviar.setPessoa(pessoa);
        final EnderecoDTO salvar = enderecoService.salvar(cepEnviar,numeroDaCasaCriado);
        return salvar;
    }

    @Transactional(readOnly = true)
    public void mapperDtoToEntity(PessoaDTO dto, Pessoa entity) {
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

        entity.getEletrodomesticos().clear();
        for (EletrodomesticoDTO eletrodomesticoDTO: dto.getEletrodomesticos()) {
            Eletrodomestico eletrodomestico = repoEletro.getOne(eletrodomesticoDTO.getId());
            entity.getEletrodomesticos().add(eletrodomestico);
        }
    }

}
