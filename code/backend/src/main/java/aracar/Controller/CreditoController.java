package aracar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    @Autowired
    private CreditoService creditoService;

    @GetMapping
    public List<CreditoDTO> listarCreditos() {
        return creditoService.listarCreditos();
    }

    @GetMapping("/{id}")
    public CreditoDTO buscarCredito(@PathVariable Long id) {
        return creditoService.buscarCredito(id);
    }

    @PostMapping
    public CreditoDTO adicionarCredito(@RequestBody CreditoDTO creditoDTO) {
        return creditoService.adicionarCredito(creditoDTO);
    }

    @PutMapping("/{id}")
    public CreditoDTO atualizarCredito(@PathVariable Long id, @RequestBody CreditoDTO creditoDTO) {
        return creditoService.atualizarCredito(id, creditoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarCredito(@PathVariable Long id) {
        creditoService.deletarCredito(id);
    }
}
