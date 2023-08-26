package br.com.fiap.grupo44.entrega.adpter.in;

import br.com.fiap.grupo44.entrega.adpter.apiDTO.ChamaResultDTO;
import br.com.fiap.grupo44.entrega.adpter.out.RandomUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cria")
public class APIPessoaRandomicaController {

    @Autowired
    RandomUseService randomUseService;
    @GetMapping
    public ChamaResultDTO Results() {
        return randomUseService.getRandomUser();
    }
}

