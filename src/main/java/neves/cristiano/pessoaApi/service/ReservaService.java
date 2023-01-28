package neves.cristiano.pessoaApi.service;

import lombok.RequiredArgsConstructor;
import neves.cristiano.pessoaApi.modelo.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservaService {
    private final CrudRepository<Reserva, String> repository;

    public Optional<Reserva> getById(String id) {
        return repository.findById(id);
    }

    public void adicionar(Reserva entity) {
        repository.save(entity);
    }
}