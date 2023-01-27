package neves.cristiano.pessoaApi.controller;

import neves.cristiano.pessoaApi.dto.ReservaDTO;
import neves.cristiano.pessoaApi.modelo.Reserva;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
    @GetMapping("{id}")
    public ResponseEntity<ReservaDTO> getById(@PathVariable String id) {
        if (id == null || id.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informado um ID!");
        }
        Reserva entity = new Reserva(id);
        entity.setPessoa("123.456.789-00");
        entity.setMesa(UUID.randomUUID().toString());
        entity.setData(LocalDate.now());
        entity.setInicio(LocalTime.now().minusMinutes(30));
        entity.setFim(LocalTime.now().plusMinutes(30));
        return ResponseEntity.ok(ReservaDTO.of(entity));
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody ReservaDTO reserva) {
        if (reserva == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma reserva válida");
        }
        if (reserva.getPessoa() == null || reserva.getPessoa().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma reserva com uma pessoa válida");
        }
        if (reserva.getMesa() == null || reserva.getMesa().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma reserva com uma mesa válida");
        }
        if (reserva.getData() == null || reserva.getData().isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma reserva com uma data válida");
        }
        if (reserva.getInicio() == null || reserva.getInicio().isAfter(LocalTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma reserva com um início válido");
        }
        if (reserva.getFim() == null || reserva.getInicio().isAfter(reserva.getFim())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma reserva com um fim válido");
        }
        if (reserva.getId() == null || reserva.getId().isBlank()) {
            reserva.setId(UUID.randomUUID().toString());
        }
        return ResponseEntity.created(URI.create("/api/reserva/" + reserva.getId())).build();
    }
}
