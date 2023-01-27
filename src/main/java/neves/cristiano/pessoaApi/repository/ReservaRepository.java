package neves.cristiano.pessoaApi.repository;

import neves.cristiano.pessoaApi.modelo.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, String> {
}
