package aracar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aracar.DTO.ClienteDTO;
import aracar.Exceptions.InvalidRequestException;
import aracar.Exceptions.ResourceNotFoundException;
import aracar.Mappers.ClienteMapper;
import aracar.Models.Cliente;
import aracar.Repository.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
            .stream()
            .map(ClienteMapper.INSTANCE::toDTO)
            .collect(Collectors.toList());
    }

    public ClienteDTO buscarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
        return ClienteMapper.INSTANCE.toDTO(cliente);
    }

    // o método abaixo pode ser refatorado da seguinte forma:
    // public ClienteDTO adicionarCliente(ClienteDTO clienteDTO) {
    //     validarClienteDTO(clienteDTO);
    //     Cliente cliente = ClienteMapper.INSTANCE.toEntity(clienteDTO);
    //     cliente = clienteRepository.save(cliente);
    //     return ClienteMapper.INSTANCE.toDTO(cliente);
    // }
    // adicionando o seguinte método de validação:
    // private void validarClienteDTO(ClienteDTO clienteDTO) {
    //     if (clienteDTO.getCpf() == null || clienteDTO.getCpf().isEmpty()) {
    //         throw new InvalidRequestException("cpf", "O CPF não pode ser nulo ou vazio");
    //     }
    //     if (clienteDTO.getNome() == null || clienteDTO.getNome().isEmpty()) {
    //         throw new InvalidRequestException("nome", "O nome não pode ser nulo ou vazio");
    //     }
    // }

    public ClienteDTO adicionarCliente(ClienteDTO clienteDTO) {
        if (clienteDTO.getCpf() == null || clienteDTO.getCpf().isEmpty()) {
            throw new InvalidRequestException("cpf", "O CPF não pode ser nulo ou vazio");
        }
        Cliente cliente = ClienteMapper.INSTANCE.toEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return ClienteMapper.INSTANCE.toDTO(cliente);
    }

    // o método acima pode ser refatorado aproveitando o método de validação:
    // public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
    //     Cliente cliente = clienteRepository.findById(id)
    //         .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
    //     validarClienteDTO(clienteDTO);
    //     atualizarClienteComDTO(cliente, clienteDTO);
    //     cliente = clienteRepository.save(cliente);
    //     return ClienteMapper.INSTANCE.toDTO(cliente);
    // }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));

        if (clienteDTO.getNome() == null || clienteDTO.getNome().isEmpty()) {
            throw new InvalidRequestException("nome", "O nome não pode ser nulo ou vazio");
        }

        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setRg(clienteDTO.getRg());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setProfissao(clienteDTO.getProfissao());
        cliente.setEmpregadores(clienteDTO.getEmpregadores());
        cliente.setRendimentos(clienteDTO.getRendimentos());
        
        cliente = clienteRepository.save(cliente);
        return ClienteMapper.INSTANCE.toDTO(cliente);
    }

    public void deletarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
        clienteRepository.delete(cliente);
    }
}
