package aracar.DTO;

import lombok.Data;

@Data
public class AutomovelDTO {
    private Long id;
    private String matricula;
    private int ano;
    private String marca;
    private String modelo;
    private String placa;
}
