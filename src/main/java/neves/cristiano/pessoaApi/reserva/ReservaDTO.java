package neves.cristiano.pessoaApi.reserva;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaDTO {
    private String id;
    private String pessoa;
    private String mesa;
    private LocalDate data;
    private LocalTime inicio;
    private LocalTime fim;

    public static ReservaDTO of(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setPessoa(reserva.getPessoa());
        dto.setMesa(reserva.getMesa());
        dto.setData(reserva.getData());
        dto.setInicio(reserva.getInicio());
        dto.setFim(reserva.getFim());
        return dto;
    }

    public Reserva toEntity() {
        Reserva entity = new Reserva();
        entity.setId(id);
        entity.setPessoa(pessoa);
        entity.setMesa(mesa);
        entity.setData(data);
        entity.setInicio(inicio);
        entity.setFim(fim);
        return entity;
    }
}
