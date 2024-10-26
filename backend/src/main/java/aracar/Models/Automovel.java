package aracar.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Automovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Interessante adicionar validações para que os campos não sejam nulos
    // @NotEmpty(message = "Matrícula não pode ser vazia")
    // private String matricula;
    // @Positive(message = "Ano deve ser um valor positivo")
    // private int ano;
    // @NotEmpty(message = "Marca não pode ser vazia")
    // private String marca;
    // @NotEmpty(message = "Modelo não pode ser vazio")
    // private String modelo;
    // @NotEmpty(message = "Placa não pode ser vazia")
    // private String placa;

    private String matricula;
    private int ano;
    private String marca;
    private String modelo;
    private String placa;
}
