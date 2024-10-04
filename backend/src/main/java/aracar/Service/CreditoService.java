package aracar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aracar.DTO.CreditoDTO;
import aracar.Exceptions.InvalidRequestException;
import aracar.Exceptions.ResourceNotFoundException;
import aracar.Mappers.CreditoMapper;
import aracar.Models.Credito;
import aracar.Repository.CreditoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditoService {

    @Autowired
    private CreditoRepository creditoRepository;

    public List<CreditoDTO> listarCreditos() {
        return creditoRepository.findAll()
            .stream()
            .map(CreditoMapper.INSTANCE::toDTO)
            .collect(Collectors.toList());
    }

    public CreditoDTO buscarCredito(Long id) {
        Credito credito = creditoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Crédito", id));
        return CreditoMapper.INSTANCE.toDTO(credito);
    }

    public CreditoDTO adicionarCredito(CreditoDTO creditoDTO) {
        if (creditoDTO.getValor() <= 0) {
            throw new InvalidRequestException("valor", "O valor do crédito deve ser positivo");
        }
        Credito credito = CreditoMapper.INSTANCE.toEntity(creditoDTO);
        credito = creditoRepository.save(credito);
        return CreditoMapper.INSTANCE.toDTO(credito);
    }

    public CreditoDTO atualizarCredito(Long id, CreditoDTO creditoDTO) {
        Credito credito = creditoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Crédito", id));

        if (creditoDTO.getValor() <= 0) {
            throw new InvalidRequestException("valor", "O valor do crédito deve ser positivo");
        }

        credito.setValor(creditoDTO.getValor());
        credito.setDataConcessao(creditoDTO.getDataConcessao());
        credito = creditoRepository.save(credito);
        return CreditoMapper.INSTANCE.toDTO(credito);
    }

    public void deletarCredito(Long id) {
        Credito credito = creditoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Crédito", id));
        creditoRepository.delete(credito);
    }
}
