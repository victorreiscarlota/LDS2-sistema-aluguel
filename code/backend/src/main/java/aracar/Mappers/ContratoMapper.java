package aracar.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import aracar.DTO.ContratoDTO;
import aracar.Models.Contrato;

import java.util.List;

@Mapper
public interface ContratoMapper {
    ContratoMapper INSTANCE = Mappers.getMapper(ContratoMapper.class);

    ContratoDTO toDTO(Contrato contrato);
    Contrato toEntity(ContratoDTO contratoDTO);

    List<ContratoDTO> toDTOList(List<Contrato> contratos);
    List<Contrato> toEntityList(List<ContratoDTO> contratoDTOs);
}
