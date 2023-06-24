package br.com.fiap.grupo44.entrega.service;

import br.com.fiap.grupo44.entrega.dto.EletrodomesticoDTO;
import br.com.fiap.grupo44.entrega.entities.Eletrodomestico;
import br.com.fiap.grupo44.entrega.repository.IEletrodomesticoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EletrodomesticoService {

    @Autowired
    private IEletrodomesticoRepository repository;

    public Page<EletrodomesticoDTO> findAll(PageRequest pagina) {
        var eletrodomesticos = repository.findAll(pagina);
        return eletrodomesticos.map(eletrodomestico -> new EletrodomesticoDTO(eletrodomestico));
    }

    public EletrodomesticoDTO findById(UUID id) {
        var eletrodomestico = repository.findById(id).orElse(null);
        return new EletrodomesticoDTO(eletrodomestico);
    }

    public EletrodomesticoDTO save(EletrodomesticoDTO eletroDomesticoDTO) {
        Eletrodomestico entity = new Eletrodomestico();
        entity.setNome(eletroDomesticoDTO.getNome());
        entity.setModelo(eletroDomesticoDTO.getModelo());
        entity.setMarca(eletroDomesticoDTO.getMarca());
        entity.setVoltagem(eletroDomesticoDTO.getVoltagem());
        entity.setPotencia(eletroDomesticoDTO.getPotencia());
        entity.setConsumo(eletroDomesticoDTO.getConsumo());

        var eletroSaved = repository.save(entity);
        return new EletrodomesticoDTO(eletroSaved);
    }

    public EletrodomesticoDTO update(UUID id, EletrodomesticoDTO eletroDomesticoDTO) {
        try {
            Eletrodomestico buscaEletrodomestico = repository.getOne(id);
            buscaEletrodomestico.setNome(eletroDomesticoDTO.getNome());
            buscaEletrodomestico.setModelo(eletroDomesticoDTO.getModelo());
            buscaEletrodomestico.setMarca(eletroDomesticoDTO.getMarca());
            buscaEletrodomestico.setVoltagem(eletroDomesticoDTO.getVoltagem());
            buscaEletrodomestico.setPotencia(eletroDomesticoDTO.getPotencia());
            buscaEletrodomestico.setConsumo(eletroDomesticoDTO.getConsumo());
            buscaEletrodomestico = repository.save(buscaEletrodomestico);

            return new EletrodomesticoDTO(buscaEletrodomestico);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Produto não encontrado, id:" + id);
        }
    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new EntityNotFoundException("Erro ao deletar eletrodomestico: " + id);
        }

    }

    public List<String> validate(EletrodomesticoDTO eletrodomesticoDTO){
        Set<ConstraintViolation<EletrodomesticoDTO>> violacoes = Validation.buildDefaultValidatorFactory().getValidator().validate(eletrodomesticoDTO);
        List<String> violacoesToList = violacoes.stream()
                .map((violacao) -> violacao.getPropertyPath() + ":" + violacao.getMessage())
                .collect(Collectors.toList());
        return violacoesToList;
    }
    public EletrodomesticoDTO calcularConsumoMedio(EletrodomesticoDTO eletrodomesticoDTO) {
        Double consumo = (eletrodomesticoDTO.getPotencia() * 24) / 1000;
        eletrodomesticoDTO.setConsumo(consumo);
        return eletrodomesticoDTO;
    }
}
