package neves.cristiano.reserva;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
