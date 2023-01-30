package neves.cristiano.pessoaApi.reserva;

import lombok.RequiredArgsConstructor;
import neves.cristiano.pessoaApi.comun.exceptions.EntidadeDuplicadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
    private final ReservaService service;

    @GetMapping("{id}")
    public ResponseEntity<ReservaDTO> getById(@PathVariable String id) {
        if (id == null || id.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informado um ID!");
        }
        Reserva entity = service.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi localizada uma reserva com o ID informado!"));
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

        try {
            service.adicionar(reserva.toEntity());
        } catch (EntidadeDuplicadaException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma reserva com o ID informado");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido");
        }
        return ResponseEntity.created(URI.create("/api/reserva/" + reserva.getId())).build();
    }
}
