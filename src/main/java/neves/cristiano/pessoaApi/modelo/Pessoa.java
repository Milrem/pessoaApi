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
    private String cpf;
    private String nome;
    private LocalDate nascimento;


}
