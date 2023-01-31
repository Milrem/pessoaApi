package neves.cristiano.mesa;

import lombok.RequiredArgsConstructor;
import neves.cristiano.comum.exceptions.EntidadeDuplicadaException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MesaService {
    private final CrudRepository<Mesa, String> repository;

    public Optional<Mesa> getById(String id) {
        return repository.findById(id);
    }

    public void adicionar(Mesa entity) throws EntidadeDuplicadaException {
        if (repository.existsById(entity.getId())) {
            throw new EntidadeDuplicadaException();
        }
        repository.save(entity);
    }
}
