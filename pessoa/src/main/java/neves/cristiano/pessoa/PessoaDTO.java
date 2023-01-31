package neves.cristiano.pessoa;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaDTO {
    private final String cpf;
    private final String nome;
    private LocalDate nascimento;

    public static PessoaDTO of(Pessoa pessoa) {
        PessoaDTO dto = new PessoaDTO(pessoa.getCpf(), pessoa.getNome());
        dto.nascimento = pessoa.getNascimento();
        return dto;
    }

    public Pessoa toEntity() {
        Pessoa entity = new Pessoa();
        entity.setCpf(cpf);
        entity.setNome(nome);
        entity.setNascimento(nascimento);
        return entity;
    }
}
