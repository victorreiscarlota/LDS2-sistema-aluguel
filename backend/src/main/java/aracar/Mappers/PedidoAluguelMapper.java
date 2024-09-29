package aracar.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import aracar.DTO.PedidoAluguelDTO;
import aracar.Models.PedidoAluguel;

import java.util.List;

@Mapper
public interface PedidoAluguelMapper {
    PedidoAluguelMapper INSTANCE = Mappers.getMapper(PedidoAluguelMapper.class);

    PedidoAluguelDTO toDTO(PedidoAluguel pedidoAluguel);
    PedidoAluguel toEntity(PedidoAluguelDTO pedidoAluguelDTO);

    List<PedidoAluguelDTO> toDTOList(List<PedidoAluguel> pedidos);
    List<PedidoAluguel> toEntityList(List<PedidoAluguelDTO> pedidoDTOs);
}
