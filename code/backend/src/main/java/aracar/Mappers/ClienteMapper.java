package aracar.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import aracar.DTO.ClienteDTO;
import aracar.Models.Cliente;

import java.util.List;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);

    List<ClienteDTO> toDTOList(List<Cliente> clientes);
    List<Cliente> toEntityList(List<ClienteDTO> clienteDTOs);
}
