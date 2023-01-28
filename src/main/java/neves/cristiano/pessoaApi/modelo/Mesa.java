package neves.cristiano.pessoaApi.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Mesa {
    @Id
    private String id;
    private String sala;
    private Integer capacidade;
}
