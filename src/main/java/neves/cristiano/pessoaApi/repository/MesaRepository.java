package neves.cristiano.pessoaApi.repository;

import neves.cristiano.pessoaApi.modelo.Mesa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends PagingAndSortingRepository<Mesa, String> {
}
