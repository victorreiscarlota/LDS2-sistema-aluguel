package aracar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import aracar.DTO.ContratoDTO;
import aracar.Service.ContratoService;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @GetMapping
    public List<ContratoDTO> listarContratos() {
        return contratoService.listarContratos();
    }

    @GetMapping("/{id}")
    public ContratoDTO buscarContrato(@PathVariable Long id) {
        return contratoService.buscarContrato(id);
    }

    @PostMapping
    public ContratoDTO adicionarContrato(@RequestBody ContratoDTO contratoDTO) {
        return contratoService.adicionarContrato(contratoDTO);
    }

    @PutMapping("/{id}")
    public ContratoDTO atualizarContrato(@PathVariable Long id, @RequestBody ContratoDTO contratoDTO) {
        return contratoService.atualizarContrato(id, contratoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarContrato(@PathVariable Long id) {
        contratoService.deletarContrato(id);
    }
}
