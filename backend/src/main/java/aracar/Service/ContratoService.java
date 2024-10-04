package aracar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aracar.DTO.ContratoDTO;
import aracar.Exceptions.InvalidRequestException;
import aracar.Exceptions.ResourceNotFoundException;
import aracar.Mappers.ContratoMapper;
import aracar.Models.Contrato;
import aracar.Repository.ContratoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    public List<ContratoDTO> listarContratos() {
        return contratoRepository.findAll()
            .stream()
            .map(ContratoMapper.INSTANCE::toDTO)
            .collect(Collectors.toList());
    }

    public ContratoDTO buscarContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contrato", id));
        return ContratoMapper.INSTANCE.toDTO(contrato);
    }

    public ContratoDTO adicionarContrato(ContratoDTO contratoDTO) {
        if (contratoDTO.getDataContrato() == null) {
            throw new InvalidRequestException("dataContrato", "A data do contrato não pode ser nula");
        }
        Contrato contrato = ContratoMapper.INSTANCE.toEntity(contratoDTO);
        contrato = contratoRepository.save(contrato);
        return ContratoMapper.INSTANCE.toDTO(contrato);
    }

    public ContratoDTO atualizarContrato(Long id, ContratoDTO contratoDTO) {
        Contrato contrato = contratoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contrato", id));

        if (contratoDTO.getTipo() == null || contratoDTO.getTipo().isEmpty()) {
            throw new InvalidRequestException("tipo", "O tipo do contrato não pode ser nulo ou vazio");
        }

        contrato.setDataContrato(contratoDTO.getDataContrato());
        contrato.setTipo(contratoDTO.getTipo());
        contrato = contratoRepository.save(contrato);
        return ContratoMapper.INSTANCE.toDTO(contrato);
    }

    public void deletarContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contrato", id));
        contratoRepository.delete(contrato);
    }
}
