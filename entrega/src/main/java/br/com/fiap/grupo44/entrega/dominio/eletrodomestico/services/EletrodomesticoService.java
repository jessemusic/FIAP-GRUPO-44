package br.com.fiap.grupo44.entrega.dominio.eletrodomestico.services;

import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.dto.EletrodomesticoDTO;
import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.entities.Eletrodomestico;
import br.com.fiap.grupo44.entrega.exception.ControllerNotFoundException;
import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.repositories.IEletrodomesticoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EletrodomesticoService {

    @Autowired
    private IEletrodomesticoRepository repository;

    public Page<EletrodomesticoDTO> findAll(EletrodomesticoDTO filtro, PageRequest pagina) {

        Specification<Eletrodomestico> specification = Specification.where(null);
        if (!StringUtils.isEmpty(filtro.getNome())) {
            specification = specification.and((root, query, builder) ->
                    builder.like(builder.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(filtro.getMarca())) {
            specification = specification.and((root, query, builder) ->
                    builder.like(builder.lower(root.get("marca")), "%" + filtro.getMarca().toLowerCase() + "%"));
        }

        if (filtro.getPotencia() != null) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("potencia"), filtro.getPotencia()));
        }

        if (!StringUtils.isEmpty(filtro.getTensao())) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("tensao"), filtro.getTensao()));
        }

        if (filtro.getIdPatchCategoria() != null) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("idPatchCategoria"), filtro.getIdPatchCategoria()));
        }

        var eletrodomesticos = repository.findAll(specification,pagina);
        return eletrodomesticos.map(eletrodomestico -> new EletrodomesticoDTO(eletrodomestico));
    }

    public EletrodomesticoDTO findById(Long id) {
        var eletrodomestico = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Pessoa não encontrada"));
        return new EletrodomesticoDTO(eletrodomestico);
    }

    public EletrodomesticoDTO save(EletrodomesticoDTO dto) {
        Eletrodomestico entity = new Eletrodomestico();
        mapperDtoToEntity(dto,entity);
        var eletroSaved = repository.save(entity);
        return new EletrodomesticoDTO(eletroSaved);
    }

    public EletrodomesticoDTO update(Long id, EletrodomesticoDTO dto) {
        try {
            Eletrodomestico buscaEletrodomestico = repository.getOne(id);
            mapperDtoToEntity(dto,buscaEletrodomestico);
            buscaEletrodomestico = repository.save(buscaEletrodomestico);

            return new EletrodomesticoDTO(buscaEletrodomestico);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Tensão não encontrado, id:" + id);
        }
    }

    public EletrodomesticoDTO updateEletrodomesticoByFields(Long id, Map<String, Object> fields) {
        Eletrodomestico existingEletro = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Eletrodomestico não encontrada"));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Eletrodomestico.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingEletro, value);
        });
        var eletroAtualizado = repository.save(existingEletro);
        return new EletrodomesticoDTO(eletroAtualizado);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new EntityNotFoundException("Erro ao deletar eletrodomestico: " + id);
        }

    }

    public List<String> validate(EletrodomesticoDTO dto){
        Set<ConstraintViolation<EletrodomesticoDTO>> violacoes = Validation.buildDefaultValidatorFactory().getValidator().validate(dto);
        List<String> violacoesToList = violacoes.stream()
                .map((violacao) -> violacao.getPropertyPath() + ":" + violacao.getMessage())
                .collect(Collectors.toList());
        return violacoesToList;
    }
    public EletrodomesticoDTO calcularConsumoMedio(EletrodomesticoDTO dto) {

        Double consumoDiario = (dto.getPotencia() * dto.getUsoDiarioEstimado()) / 1000;
        if (consumoDiario > 0){
            dto.setConsumoDiario(consumoDiario);
            dto.setConsumoMensal(consumoDiario * dto.getUsoDiasEstimados());

        }
        return dto;
    }

    private void  mapperDtoToEntity(EletrodomesticoDTO dto, Eletrodomestico entity) {
        entity.setNome(dto.getNome());
        entity.setModelo(dto.getModelo());
        entity.setMarca(dto.getMarca());
        entity.setTensao(dto.getTensao());
        entity.setPotencia(dto.getPotencia());
        entity.setUsoDiarioEstimado(dto.getUsoDiarioEstimado());
        entity.setUsoDiasEstimados(dto.getUsoDiasEstimados());
        entity.setConsumoDiario(dto.getConsumoDiario());
        entity.setConsumoMensal(dto.getConsumoMensal());
        entity.setIdPatchCategoria(dto.getIdPatchCategoria());
    }

    public Set<EletrodomesticoDTO> selecionarEletrodomesticosAleatoriamente() {
        Set<Eletrodomestico> eletrodomesticos = new HashSet<>(repository.findAll());
        Map<Long, Set<Eletrodomestico>> eletrodomesticosPorCategoria = eletrodomesticos.stream()
                .collect(Collectors.groupingBy(Eletrodomestico::getIdPatchCategoria, Collectors.toSet()));

        Random random = new Random();
        List<EletrodomesticoDTO> eletrodomesticosSelecionados = new ArrayList<>();

        eletrodomesticosPorCategoria.values().forEach(eletrodomesticosCategoria -> {
            List<Eletrodomestico> eletrodomesticosCategoriaLista = new ArrayList<>(eletrodomesticosCategoria);
            if (!eletrodomesticosCategoriaLista.isEmpty()) {
                Collections.shuffle(eletrodomesticosCategoriaLista);
                Eletrodomestico eletrodomesticoAleatorio = eletrodomesticosCategoriaLista.get(random.nextInt(eletrodomesticosCategoriaLista.size()));
                eletrodomesticosSelecionados.add(new EletrodomesticoDTO(eletrodomesticoAleatorio));
            }
        });

        int quantidadeTotal = Math.min(random.nextInt(12) + 1, eletrodomesticosSelecionados.size());
        return eletrodomesticosSelecionados.stream()
                .limit(quantidadeTotal)
                .collect(Collectors.toSet());
    }



}
