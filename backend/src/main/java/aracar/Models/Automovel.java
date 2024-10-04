package aracar.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Automovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;
    private int ano;
    private String marca;
    private String modelo;
    private String placa;
}
