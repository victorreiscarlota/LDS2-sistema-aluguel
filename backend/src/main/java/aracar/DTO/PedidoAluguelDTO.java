package aracar.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class PedidoAluguelDTO {
    private Long id;
    private Date dataPedido;
    private String status;
    private Long clienteId;
}
