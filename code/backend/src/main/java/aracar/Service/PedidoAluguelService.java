package aracar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoAluguelService {

    @Autowired
    private PedidoAluguelRepository pedidoAluguelRepository;

    public List<PedidoAluguelDTO> listarPedidos() {
        return pedidoAluguelRepository.findAll()
            .stream()
            .map(PedidoAluguelMapper.INSTANCE::toDTO)
            .collect(Collectors.toList());
    }

    public PedidoAluguelDTO buscarPedido(Long id) {
        PedidoAluguel pedido = pedidoAluguelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("PedidoAluguel", id));
        return PedidoAluguelMapper.INSTANCE.toDTO(pedido);
    }

    public PedidoAluguelDTO adicionarPedido(PedidoAluguelDTO pedidoDTO) {
        if (pedidoDTO.getDataPedido() == null) {
            throw new InvalidRequestException("dataPedido", "A data do pedido não pode ser nula");
        }
        PedidoAluguel pedido = PedidoAluguelMapper.INSTANCE.toEntity(pedidoDTO);
        pedido = pedidoAluguelRepository.save(pedido);
        return PedidoAluguelMapper.INSTANCE.toDTO(pedido);
    }

    public PedidoAluguelDTO atualizarPedido(Long id, PedidoAluguelDTO pedidoDTO) {
        PedidoAluguel pedido = pedidoAluguelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("PedidoAluguel", id));

        if (pedidoDTO.getStatus() == null || pedidoDTO.getStatus().isEmpty()) {
            throw new InvalidRequestException("status", "O status do pedido não pode ser nulo ou vazio");
        }

        pedido.setStatus(pedidoDTO.getStatus());
        pedido.setDataPedido(pedidoDTO.getDataPedido());
        pedido = pedidoAluguelRepository.save(pedido);
        return PedidoAluguelMapper.INSTANCE.toDTO(pedido);
    }

    public void deletarPedido(Long id) {
        PedidoAluguel pedido = pedidoAluguelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("PedidoAluguel", id));
        pedidoAluguelRepository.delete(pedido);
    }
}
