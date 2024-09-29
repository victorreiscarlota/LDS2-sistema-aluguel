package aracar.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rg;
    private String cpf;
    private String nome;
    private String endereco;
    private String profissao;

    @ElementCollection
    private List<String> empregadores;

    @ElementCollection
    private List<Float> rendimentos;
}
