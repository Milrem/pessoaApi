package neves.cristiano.pessoaApi.controller;

import neves.cristiano.pessoaApi.dto.MesaDTO;
import neves.cristiano.pessoaApi.modelo.Mesa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/mesa")
public class MesaController {
    @GetMapping("{id}")
    public ResponseEntity<MesaDTO> getById(@PathVariable String id) {
        if (id == null || id.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informado um ID!");
        }

        Mesa mesa = new Mesa(id, "Sala01", 2);
        return ResponseEntity.ok(MesaDTO.of(mesa));
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody MesaDTO mesa) {
        if (mesa == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma mesa válida");
        }
        if (mesa.getSala() == null || mesa.getSala().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma mesa com uma sala válida");
        }
        if (mesa.getCapacidade() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma mesa com uma capacidade válida");
        }
        if (mesa.getId() == null || mesa.getId().isBlank()) {
            mesa.setId(UUID.randomUUID().toString());
        }
        return ResponseEntity.created(URI.create("/api/mesa/" + mesa.getId())).build();
    }
}
