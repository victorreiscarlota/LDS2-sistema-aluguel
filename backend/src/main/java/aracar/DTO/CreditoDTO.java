package aracar.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class CreditoDTO {
    private Long id;
    private float valor;
    private Date dataConcessao;
}
