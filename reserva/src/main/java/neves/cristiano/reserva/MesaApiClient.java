package neves.cristiano.reserva;

import org.springframework.web.client.RestTemplate;

public class MesaApiClient {
    String endpoint = "http://localhost:8082/api/mesa/";
    RestTemplate restTemplate = new RestTemplate();

    public boolean existeMesa(String id) {
        return restTemplate.getForEntity(endpoint.concat(id), String.class)
                .getStatusCode()
                .is2xxSuccessful();
    }
}
