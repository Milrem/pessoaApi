package neves.cristiano.pessoaApi.modelo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Pessoa {

    @Id
    private final String cpf;
    private final String nome;
    private LocalDate nascimento;


}
