package neves.cristiano.pessoaApi.mesa;

import neves.cristiano.pessoaApi.mesa.Mesa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends PagingAndSortingRepository<Mesa, String> {
}
