package neves.cristiano.pessoaApi.controller;

import lombok.RequiredArgsConstructor;
import neves.cristiano.pessoaApi.dto.MesaDTO;
import neves.cristiano.pessoaApi.exceptions.EntidadeDuplicadaException;
import neves.cristiano.pessoaApi.modelo.Mesa;
import neves.cristiano.pessoaApi.service.MesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mesa")
public class MesaController {
    private final MesaService service;

    @GetMapping("{id}")
    public ResponseEntity<MesaDTO> getById(@PathVariable String id) {
        if (id == null || id.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informado um ID!");
        }

        Mesa entity = service.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi localizada uma mesa com o ID informado!"));
        return ResponseEntity.ok(MesaDTO.of(entity));
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
        try {
            service.adicionar(mesa.toEntity());
        } catch (EntidadeDuplicadaException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma mesa com o ID informado");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido");
        }
        return ResponseEntity.created(URI.create("/api/mesa/" + mesa.getId())).build();
    }
}
