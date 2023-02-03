package neves.cristiano.reserva;

import lombok.RequiredArgsConstructor;
import neves.cristiano.comum.exceptions.EntidadeDuplicadaException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservaService {
    private final CrudRepository<Reserva, String> repository;
    private final PessoaApiClient pessoaApiClient;

    public Optional<Reserva> getById(String id) {
        return repository.findById(id);
    }

    public void adicionar(Reserva entity) throws EntidadeDuplicadaException, ReservaInvalidaException {
        if (repository.existsById(entity.getId())) {
            throw new EntidadeDuplicadaException();
        }
        /**
         * Para efetivar a reserva, precisa:
         * - Confirmar que a mesa existe (consultar a API Mesa)
         * - Confirmar que a pessoa existe (consultar a API Pessoa)
         * ?- Confirmar que a agenda reservada está disponível.
         */

        try {
            if (!new MesaApiClient().existeMesa(entity.getMesa())) {
                throw new ReservaInvalidaException("Mesa não localizada");
            }
        } catch (RestClientException e) {
            throw new ReservaInvalidaException("Api de Mesa não disponível");
        }

        try {
            if (!pessoaApiClient.existePessoa(entity.getPessoa())) {
                throw new ReservaInvalidaException("Pessoa não localizada");
            }
        } catch (RestClientException e) {
            throw new ReservaInvalidaException("Api de Pessoa não disponível");
        }

        repository.save(entity);
    }
}
