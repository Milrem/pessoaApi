package neves.cristiano.pessoaApi.pessoa;

import neves.cristiano.pessoaApi.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String> {
}
