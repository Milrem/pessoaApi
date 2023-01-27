package neves.cristiano.pessoaApi.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Mesa {
    @Id
    private final String id;
    private final String sala;
    private final Integer capacidade;
}
