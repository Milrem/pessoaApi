package neves.cristiano.pessoaApi.service;

import lombok.RequiredArgsConstructor;
import neves.cristiano.pessoaApi.exceptions.EntidadeDuplicadaException;
import neves.cristiano.pessoaApi.modelo.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PessoaService {
    private final CrudRepository<Pessoa, String> repository;

    public Optional<Pessoa> getById(String id) {
        return repository.findById(id);
    }

    public void adicionar(Pessoa entity) throws EntidadeDuplicadaException {
        if (repository.existsById(entity.getCpf())) {
            throw new EntidadeDuplicadaException();
        }
        repository.save(entity);
    }
}
