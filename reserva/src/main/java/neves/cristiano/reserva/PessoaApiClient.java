package neves.cristiano.reserva;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PessoaApiClient {
    private final static String ENDPOINT = "http://{host}:{port}/api/pessoa/{cpf}";

    private final EurekaClient eurekaClient;
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean existePessoa(String cpf) {
        InstanceInfo apiInstanceInfo = eurekaClient.getApplication("rooms-pessoa")
                .getInstances().stream().findAny().orElseThrow(() -> new RemoteAccessException("Pessoa Api indisponível"));
        return restTemplate.getForEntity(ENDPOINT, String.class, apiInstanceInfo.getHostName(), apiInstanceInfo.getPort(), cpf)
                .getStatusCode()
                .is2xxSuccessful();
    }
}
