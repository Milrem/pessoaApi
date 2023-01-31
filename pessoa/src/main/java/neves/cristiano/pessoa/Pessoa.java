package neves.cristiano.pessoa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Pessoa {

    @Id
    private String cpf;
    private String nome;
    private LocalDate nascimento;


}
