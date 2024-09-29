package aracar.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dataContrato;

    private String tipo;

    @OneToMany
    private List<Automovel> automoveis;

    @ManyToOne
    private Credito credito;
}
