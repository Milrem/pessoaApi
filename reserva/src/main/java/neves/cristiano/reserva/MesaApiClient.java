package neves.cristiano.reserva;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class MesaApiClient {
    private final static String ENDPOINT = "http://{host}:{port}/api/mesa/{id}";

    private final EurekaClient eurekaClient;
    private final RestTemplate restTemplate;

    public boolean existeMesa(String id) {
        InstanceInfo apiInstanceInfo = eurekaClient.getApplication("rooms-mesa")
                .getInstances().stream().findAny().orElseThrow(() -> new RemoteAccessException("Mesa Api indisponível"));
        return restTemplate.getForEntity(ENDPOINT, String.class, apiInstanceInfo.getHostName(), apiInstanceInfo.getPort(), id)
                .getStatusCode()
                .is2xxSuccessful();
    }
}
