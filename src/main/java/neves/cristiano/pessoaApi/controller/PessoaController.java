package neves.cristiano.pessoaApi.controller;

import neves.cristiano.pessoaApi.dto.PessoaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody PessoaDTO pessoa) {
        if (pessoa == null || pessoa.getCpf() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma pessoa válida");
        }
        String clearCpf = pessoa.getCpf().replaceAll("[\\.-]", "");
        if (clearCpf.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma pessoa com CPF válido");
        }
        if (pessoa.getNome() == null || pessoa.getNome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informada uma pessoa com Nome válido");
        }
        return ResponseEntity.created(URI.create("/api/pessoa/" + clearCpf)).build();
    }
}
