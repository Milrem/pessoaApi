package neves.cristiano.pessoaApi.controller;

import neves.cristiano.pessoaApi.modelo.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    @GetMapping("{cpf}")
    public ResponseEntity<Pessoa> getByCpf(@PathVariable String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi informado um CPF!");
        }
        String clearCpf = cpf.replaceAll("[\\.-]", "");
        Pessoa entity = new Pessoa(clearCpf, "Pessoa Fake");
        return ResponseEntity.ok(entity);
    }
}
