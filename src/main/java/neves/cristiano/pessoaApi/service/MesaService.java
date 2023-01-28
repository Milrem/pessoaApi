package neves.cristiano.pessoaApi.service;

import lombok.RequiredArgsConstructor;
import neves.cristiano.pessoaApi.modelo.Mesa;
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

    public void adicionar(Mesa entity) {
        repository.save(entity);
    }
}
