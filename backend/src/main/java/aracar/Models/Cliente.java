package aracar.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotEmpty(message = "RG não pode ser nulo")
    //private String rg;
    // @NotEmpty(message = "CPF não pode ser nulo")
    // private String cpf;
    // @NotEmpty(message = "Nome não pode ser nulo")
    // private String nome;
    //mesmo que o código abaixo, mas com a anotação @NotEmpty, que é uma anotação do jakarta mais inteligível que importar a validation.constraints.NotEmpty

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
