package br.com.fiap.grupo44.entrega.controller;

import br.com.fiap.grupo44.entrega.dto.EletrodomesticoDTO;
import br.com.fiap.grupo44.entrega.service.EletrodomesticoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eletrodomesticos")
public class EletrodomesticoController {


    @Autowired
    private EletrodomesticoService eletrodomesticoService;

    @GetMapping
    public ResponseEntity<Page<EletrodomesticoDTO>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho
    ) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var eletrodomesticos = eletrodomesticoService.findAll(pageRequest);
        return ResponseEntity.ok(eletrodomesticos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EletrodomesticoDTO> findById(@PathVariable Long id) {
        var eletrodomestico = eletrodomesticoService.findById(id);
        return ResponseEntity.ok(eletrodomestico);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody EletrodomesticoDTO eletroDomesticoDTO) {
        List<String> violacoesToList = eletrodomesticoService.validate(eletroDomesticoDTO);
        if (!violacoesToList.isEmpty()) {
            return ResponseEntity.badRequest().body(violacoesToList);
        }
        eletroDomesticoDTO = eletrodomesticoService.calcularConsumoMedio(eletroDomesticoDTO);
        var eletrodomesticoSaved = eletrodomesticoService.save(eletroDomesticoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((eletrodomesticoSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(eletrodomesticoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody EletrodomesticoDTO eletroDomesticoDTO, @PathVariable Long id) {
        List<String> violacoesToList = eletrodomesticoService.validate(eletroDomesticoDTO);
        if (!violacoesToList.isEmpty()) {
            return ResponseEntity.badRequest().body(violacoesToList);
        }
        eletroDomesticoDTO = eletrodomesticoService.calcularConsumoMedio(eletroDomesticoDTO);
        var eletrodomesticoUpdated = eletrodomesticoService.update(id, eletroDomesticoDTO);
        return  ResponseEntity.ok(eletrodomesticoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        eletrodomesticoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
