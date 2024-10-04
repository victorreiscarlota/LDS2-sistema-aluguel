package aracar.DTO;

import lombok.Data;
import java.util.List;

@Data
public class ClienteDTO {
    private Long id;
    private String rg;
    private String cpf;
    private String nome;
    private String endereco;
    private String profissao;
    private List<String> empregadores;
    private List<Float> rendimentos;
}

