package aracar.DTO;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class ContratoDTO {
    private Long id;
    private Date dataContrato;
    private String tipo;
    private List<Long> automovelIds;
    private Long creditoId;
}

