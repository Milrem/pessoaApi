package neves.cristiano.mesa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends PagingAndSortingRepository<Mesa, String> {
}
