package aracar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import aracar.DTO.AutomovelDTO;
import aracar.Service.AutomovelService;

import java.util.List;

@RestController
@RequestMapping("/api/automoveis")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    @GetMapping
    public List<AutomovelDTO> listarAutomoveis() {
        return automovelService.listarAutomoveis();
    }

    @GetMapping("/{id}")
    public AutomovelDTO buscarAutomovel(@PathVariable Long id) {
        return automovelService.buscarAutomovel(id);
    }

    @PostMapping
    public AutomovelDTO adicionarAutomovel(@RequestBody AutomovelDTO automovelDTO) {
        return automovelService.adicionarAutomovel(automovelDTO);
    }

    @PutMapping("/{id}")
    public AutomovelDTO atualizarAutomovel(@PathVariable Long id, @RequestBody AutomovelDTO automovelDTO) {
        return automovelService.atualizarAutomovel(id, automovelDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarAutomovel(@PathVariable Long id) {
        automovelService.deletarAutomovel(id);
    }
}
