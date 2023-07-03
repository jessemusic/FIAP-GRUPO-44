package br.com.fiap.grupo44.entrega.controller;


import br.com.fiap.grupo44.entrega.dto.PessoaDTO;
import br.com.fiap.grupo44.entrega.dto.PessoaPatchDTO;
import br.com.fiap.grupo44.entrega.entities.Pessoa;
import br.com.fiap.grupo44.entrega.sevices.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho
    ){
        PageRequest pageRequest = PageRequest.of(pagina,tamanho);
        var pessoas = pessoaService.findAll(pageRequest);
        return ResponseEntity.ok(pessoas);
    }
    @GetMapping("/{id}")
    public PessoaDTO getProductById(@PathVariable Long id) {
        return pessoaService.findById(id);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> insert(@RequestBody PessoaDTO pessoa){

        pessoa = pessoaService.insert(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@RequestBody PessoaDTO pessoaDTO,@PathVariable Long id){
        return ResponseEntity.ok(pessoaService.update(id,pessoaDTO));
    }

    @PatchMapping("/{id}")
    public PessoaPatchDTO updatePessoaFiedls(@PathVariable Long id, @RequestBody  Map<String, Object> fields){
        return pessoaService.updatePessoaByFields(id,fields);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Long deleteProduct(@PathVariable Long id) {
        return pessoaService.deletePessoa(id);
    }

}
