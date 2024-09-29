package aracar.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import aracar.DTO.CreditoDTO;
import aracar.Models.Credito;

import java.util.List;

@Mapper
public interface CreditoMapper {
    CreditoMapper INSTANCE = Mappers.getMapper(CreditoMapper.class);

    CreditoDTO toDTO(Credito credito);
    Credito toEntity(CreditoDTO creditoDTO);

    List<CreditoDTO> toDTOList(List<Credito> creditos);
    List<Credito> toEntityList(List<CreditoDTO> creditoDTOs);
}
