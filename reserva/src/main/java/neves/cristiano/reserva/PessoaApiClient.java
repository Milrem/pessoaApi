package neves.cristiano.reserva;

import org.springframework.web.client.RestTemplate;

public class PessoaApiClient {
    String endpoint = "http://localhost:8081/api/pessoa/";
    RestTemplate restTemplate = new RestTemplate();

    public boolean existePessoa(String cpf) {
        return restTemplate.getForEntity(endpoint.concat(cpf), String.class)
                .getStatusCode()
                .is2xxSuccessful();
    }
}
