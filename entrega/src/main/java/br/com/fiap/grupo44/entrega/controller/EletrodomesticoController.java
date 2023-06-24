package br.com.fiap.grupo44.entrega.controller;

import br.com.fiap.grupo44.entrega.dto.EletrodomesticoDTO;
import br.com.fiap.grupo44.entrega.service.EletrodomesticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

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
    public ResponseEntity<EletrodomesticoDTO> findById(@PathVariable UUID id) {
        var eletrodomestico = eletrodomesticoService.findById(id);
        return ResponseEntity.ok(eletrodomestico);
    }

    @PostMapping
    public ResponseEntity<EletrodomesticoDTO> save(@RequestBody EletrodomesticoDTO eletrodomestico) {
        var eletrodomesticoSaved = eletrodomesticoService.save(eletrodomestico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((eletrodomesticoSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(eletrodomesticoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EletrodomesticoDTO> update(@RequestBody EletrodomesticoDTO eletroDomesticoDTO, @PathVariable UUID id) {
        var eletrodomesticoUpdated = eletrodomesticoService.update(id, eletroDomesticoDTO);
        return  ResponseEntity.ok(eletrodomesticoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        eletrodomesticoService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
