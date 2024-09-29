package aracar.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import aracar.DTO.AutomovelDTO;
import aracar.Models.Automovel;

import java.util.List;

@Mapper
public interface AutomovelMapper {
    AutomovelMapper INSTANCE = Mappers.getMapper(AutomovelMapper.class);

    AutomovelDTO toDTO(Automovel automovel);
    Automovel toEntity(AutomovelDTO automovelDTO);

    List<AutomovelDTO> toDTOList(List<Automovel> automoveis);
    List<Automovel> toEntityList(List<AutomovelDTO> automovelDTOs);
}
