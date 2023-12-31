package br.com.fiap.grupo44.entrega.dominio.eletrodomestico.controllers;

import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.dto.EletrodomesticoDTO;
import br.com.fiap.grupo44.entrega.dominio.eletrodomestico.services.EletrodomesticoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/eletrodomesticos",produces = {"application/json"})
//@Tag(name = "Eletrodomesticos")
@Tag(name = "API ELETRODOMÉSTICO")
public class EletrodomesticoController {


    @Autowired
    private EletrodomesticoService eletrodomesticoService;
    @Operation(summary = "Retorna lista de eletrodomesticos paginada podendo ser filtrada por pagina,tamanho,nome,marca,modelo,tensao,categoria",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @GetMapping
    public ResponseEntity<Page<EletrodomesticoDTO>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) Double potencia,
            @RequestParam(required = false) String tensao,
            @RequestParam(required = false) Long categoria
    ) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        EletrodomesticoDTO filtro = new EletrodomesticoDTO();
        filtro.setNome(nome);
        filtro.setMarca(marca);
        filtro.setModelo(modelo);
        filtro.setPotencia(potencia);
        filtro.setTensao(tensao);
        filtro.setIdPatchCategoria(categoria);
        var eletrodomesticos = eletrodomesticoService.findAll(filtro,pageRequest);
        return ResponseEntity.ok(eletrodomesticos);
    }
    @Operation(summary = "Consulta eletrodomestico por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @GetMapping("/{id}")
    public ResponseEntity<EletrodomesticoDTO> findById(@PathVariable Long id) {
        var eletrodomestico = eletrodomesticoService.findById(id);
        return ResponseEntity.ok(eletrodomestico);
    }

    @Operation(summary = "Retorna pacote com numero aleatório de 1 a 12 eletrodomésticos de categorias diferentes",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @GetMapping("/aleatorios")
    public ResponseEntity<Set<EletrodomesticoDTO>> getEletrodomesticosAleatorios() {
        Set<EletrodomesticoDTO> eletrodomesticos = eletrodomesticoService.selecionarEletrodomesticosAleatoriamente();
        if (eletrodomesticos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Status 204 No Content
        }
        return ResponseEntity.ok(eletrodomesticos); // Status 200 OK
    }

    @Operation(summary = "Insere eletrodomestico",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
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

    @Operation(summary = "Atualiza eletrodomestico",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
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
    @Operation(summary = "Atualiza atributo especifico do eletrodomestico",method = "PATCH")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @PatchMapping("/{id}")
    public EletrodomesticoDTO updateEletrodomesticoFiedls(@PathVariable Long id, @RequestBody Map<String, Object> fields){
        return eletrodomesticoService.updateEletrodomesticoByFields(id,fields);
    }
    @Operation(summary = "Deleta eletrodomestico",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Erro no seervio")})
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        eletrodomesticoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
