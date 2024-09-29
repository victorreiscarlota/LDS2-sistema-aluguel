package aracar.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import aracar.DTO.PedidoAluguelDTO;
import aracar.Service.PedidoAluguelService;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoAluguelController {

    @Autowired
    private PedidoAluguelService pedidoAluguelService;

    @GetMapping
    public List<PedidoAluguelDTO> listarPedidos() {
        return pedidoAluguelService.listarPedidos();
    }

    @GetMapping("/{id}")
    public PedidoAluguelDTO buscarPedido(@PathVariable Long id) {
        return pedidoAluguelService.buscarPedido(id);
    }

    @PostMapping
    public PedidoAluguelDTO adicionarPedido(@RequestBody PedidoAluguelDTO pedidoDTO) {
        return pedidoAluguelService.adicionarPedido(pedidoDTO);
    }

    @PutMapping("/{id}")
    public PedidoAluguelDTO atualizarPedido(@PathVariable Long id, @RequestBody PedidoAluguelDTO pedidoDTO) {
        return pedidoAluguelService.atualizarPedido(id, pedidoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarPedido(@PathVariable Long id) {
        pedidoAluguelService.deletarPedido(id);
    }
}
