package neves.cristiano.pessoaApi.mesa;

import lombok.Data;

@Data
public class MesaDTO {
    private String id;
    private String sala;
    private int capacidade;

    public static MesaDTO of(Mesa mesa) {
        MesaDTO dto = new MesaDTO();
        dto.setId(mesa.getId());
        dto.setSala(mesa.getSala());
        dto.setCapacidade(mesa.getCapacidade());
        return dto;
    }

    public Mesa toEntity() {
        Mesa mesa = new Mesa();
        mesa.setId(id);
        mesa.setSala(sala);
        mesa.setCapacidade(capacidade);
        return mesa;
    }
}
