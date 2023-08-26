package br.com.fiap.grupo44.entrega.dominio.pessoa.utilbancocep.controller;

import br.com.fiap.grupo44.entrega.dominio.pessoa.utilbancocep.entities.CriaCepAutomatico;
import br.com.fiap.grupo44.entrega.dominio.pessoa.utilbancocep.service.BuscaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/testaCep")
public class buscaCepController {

    @Autowired
    private BuscaCepService buscaCepService;

    @GetMapping("/testa")
    public ResponseEntity<String>  findById(){
        Long numero = (long)(Math.random()*1019411) +1;
        CriaCepAutomatico criaCepAutomatico = buscaCepService.findById(numero);
        return ResponseEntity.ok().body( "Cep criado = "+ criaCepAutomatico.getCep());
    }


}
