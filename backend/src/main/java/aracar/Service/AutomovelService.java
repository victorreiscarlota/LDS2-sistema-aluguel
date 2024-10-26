package aracar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aracar.DTO.AutomovelDTO;
import aracar.Exceptions.InvalidRequestException;
import aracar.Exceptions.ResourceNotFoundException;
import aracar.Mappers.AutomovelMapper;
import aracar.Models.Automovel;
import aracar.Repository.AutomovelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutomovelService {

    @Autowired
    private AutomovelRepository automovelRepository;

    public List<AutomovelDTO> listarAutomoveis() {
        return automovelRepository.findAll()
            .stream()
            .map(AutomovelMapper.INSTANCE::toDTO)
            .collect(Collectors.toList());
    }

    public AutomovelDTO buscarAutomovel(Long id) {
        Automovel automovel = automovelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Automóvel", id));
        return AutomovelMapper.INSTANCE.toDTO(automovel);
    }

    // O método abaixo poderia ser refatorado da seguinte forma:
    // public AutomovelDTO adicionarAutomovel(AutomovelDTO automovelDTO) {
    //     validarAutomovelDTO(automovelDTO);
    //     Automovel automovel = AutomovelMapper.INSTANCE.toEntity(automovelDTO);
    //     automovel = automovelRepository.save(automovel);
    //     return AutomovelMapper.INSTANCE.toDTO(automovel);
    // }
    public AutomovelDTO adicionarAutomovel(AutomovelDTO automovelDTO) {
        if (automovelDTO.getMatricula() == null || automovelDTO.getMatricula().isEmpty()) {
            throw new InvalidRequestException("matricula", "A matrícula não pode ser nula ou vazia");
        }
        Automovel automovel = AutomovelMapper.INSTANCE.toEntity(automovelDTO);
        automovel = automovelRepository.save(automovel);
        return AutomovelMapper.INSTANCE.toDTO(automovel);
    }

    // O método abaixo poderia ser refatorado da seguinte forma:
    // public AutomovelDTO atualizarAutomovel(Long id, AutomovelDTO automovelDTO) {
    //     Automovel automovel = automovelRepository.findById(id)
    //         .orElseThrow(() -> new ResourceNotFoundException("Automóvel", id));
    //     validarAutomovelDTO(automovelDTO);
    //     atualizarAutomovelComDTO(automovel, automovelDTO);
    //     automovel = automovelRepository.save(automovel);
    //     return AutomovelMapper.INSTANCE.toDTO(automovel);
    // }
    public AutomovelDTO atualizarAutomovel(Long id, AutomovelDTO automovelDTO) {
        Automovel automovel = automovelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Automóvel", id));

        if (automovelDTO.getModelo() == null || automovelDTO.getModelo().isEmpty()) {
            throw new InvalidRequestException("modelo", "O modelo do automóvel não pode ser nulo ou vazio");
        }

        automovel.setMarca(automovelDTO.getMarca());
        automovel.setModelo(automovelDTO.getModelo());
        automovel.setAno(automovelDTO.getAno());
        automovel.setPlaca(automovelDTO.getPlaca());
        automovel.setMatricula(automovelDTO.getMatricula());

        automovel = automovelRepository.save(automovel);
        return AutomovelMapper.INSTANCE.toDTO(automovel);
    }

    public void deletarAutomovel(Long id) {
        Automovel automovel = automovelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Automóvel", id));
        automovelRepository.delete(automovel);
    }

    // métodos auxiliares referenciados nas novas implementações
    // private void validarAutomovelDTO(AutomovelDTO automovelDTO) {
    //     if (automovelDTO.getMatricula() == null || automovelDTO.getMatricula().isEmpty()) {
    //         throw new InvalidRequestException("matricula", "A matrícula não pode ser nula ou vazia");
    //     }
    //     if (automovelDTO.getMarca() == null || automovelDTO.getMarca().isEmpty()) {
    //         throw new InvalidRequestException("marca", "A marca não pode ser nula ou vazia");
    //     }
    //     if (automovelDTO.getModelo() == null || automovelDTO.getModelo().isEmpty()) {
    //         throw new InvalidRequestException("modelo", "O modelo não pode ser nulo ou vazio");
    //     }
    //     if (automovelDTO.getPlaca() == null || automovelDTO.getPlaca().isEmpty()) {
    //         throw new InvalidRequestException("placa", "A placa não pode ser nula ou vazia");
    //     }
    // }

    // private void atualizarAutomovelComDTO(Automovel automovel, AutomovelDTO automovelDTO) {
    //     automovel.setMatricula(automovelDTO.getMatricula());
    //     automovel.setAno(automovelDTO.getAno());
    //     automovel.setMarca(automovelDTO.getMarca());
    //     automovel.setModelo(automovelDTO.getModelo());
    //     automovel.setPlaca(automovelDTO.getPlaca());
    // }
}
