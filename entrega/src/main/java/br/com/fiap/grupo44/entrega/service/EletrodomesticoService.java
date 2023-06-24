package br.com.fiap.grupo44.entrega.service;
import br.com.fiap.grupo44.entrega.dto.EletrodomesticoDTO;
import br.com.fiap.grupo44.entrega.entities.Eletrodomestico;
import br.com.fiap.grupo44.entrega.repository.IEletrodomesticoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;

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
            buscaEletrodomestico.setVoltagem(eletroDomesticoDTO.getPotencia());
            buscaEletrodomestico.setPotencia(eletroDomesticoDTO.getPotencia());
            buscaEletrodomestico.setConsumo(eletroDomesticoDTO.getConsumo());
            buscaEletrodomestico = repository.save(buscaEletrodomestico);

            return new EletrodomesticoDTO(buscaEletrodomestico);
        } catch (EntityNotFoundException e) {
            throw  new EntityNotFoundException("Produto não encontrado, id:" + id);
        }
    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new EntityNotFoundException("Erro ao deletar eletrodomestico: " + id);
        }

    }
}
