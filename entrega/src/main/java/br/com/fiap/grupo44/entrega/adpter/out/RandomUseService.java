package br.com.fiap.grupo44.entrega.adpter.out;

import br.com.fiap.grupo44.entrega.dto.apiDTO.ChamaResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomUseService {

    @Value("${api-comunication.host}")
    private String apiHost;

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    public ChamaResultDTO getRandomUser() {
        ResponseEntity<ChamaResultDTO> response = restTemplate.getForEntity(apiHost ,ChamaResultDTO.class);
        return response.getBody();

    }
}

