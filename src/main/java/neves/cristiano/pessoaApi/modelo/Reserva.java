package neves.cristiano.pessoaApi.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Reserva {
    @Id
    private final String id;
    private String pessoa;
    private String mesa;
    private LocalDate data;
    private LocalTime inicio;
    private LocalTime fim;
}
