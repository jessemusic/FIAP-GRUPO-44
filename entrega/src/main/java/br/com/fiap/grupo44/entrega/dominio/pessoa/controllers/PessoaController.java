package br.com.fiap.grupo44.entrega.dominio.pessoa.controllers;

import br.com.fiap.grupo44.entrega.dominio.pessoa.dto.PessoaDTO;
import br.com.fiap.grupo44.entrega.dominio.pessoa.dto.PessoaPatchDTO;
import br.com.fiap.grupo44.entrega.dominio.pessoa.sevices.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/pessoas",produces = {"application/json"})
//@Tag(name = "open-apí")
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService){this.pessoaService = pessoaService;}


    @Operation(summary = "Lista todas as pessoas cadastradas no banco dedados",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho
    ){
        PageRequest pageRequest = PageRequest.of(pagina,tamanho);
        var pessoas = pessoaService.findAll(pageRequest);
        return ResponseEntity.ok(pessoas);
    }
    @Operation(summary = "Consulta pessoa passando o id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @GetMapping("/{id}")
    public PessoaDTO getProductById(@PathVariable Long id) {
        return pessoaService.findById(id);
    }

    @Operation(summary = "Cadatrado de pessoas",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @PostMapping
    public ResponseEntity<PessoaDTO> insert(@RequestBody PessoaDTO pessoa){
        pessoa = pessoaService.insert(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @Operation(summary = "Atualiza pessoa em todos os campos passando o id da pessoa",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@RequestBody PessoaDTO pessoaDTO,@PathVariable Long id){
        return ResponseEntity.ok(pessoaService.update(id,pessoaDTO));
    }

    @Operation(summary = "Atualiza pessoa em únicio campo desejado",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @PatchMapping("/{id}")
    public PessoaPatchDTO updatePessoaFiedls(@PathVariable Long id, @RequestBody Map<String, Object> fields){
        return pessoaService.updatePessoaByFields(id,fields);
    }

    @Operation(summary = "Apaga do banco de dados passando, apenas o id da pessoa",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Long deleteProduct(@PathVariable Long id) {
        return pessoaService.deletePessoa(id);
    }

    @Operation(summary = "Cadatrado de pessoas automárico",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @PostMapping("/cria")
    public ResponseEntity<PessoaDTO> insertAndCria(){
        PessoaDTO pessoaDTO = pessoaService.insertAndCria();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pessoaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoaDTO);
    }

}
