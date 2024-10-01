package aracar;

import aracar.Controller.AutomovelController;
import aracar.DTO.AutomovelDTO;
import aracar.Service.AutomovelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AutomovelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AutomovelService automovelService;

    @InjectMocks
    private AutomovelController automovelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(automovelController).build();
    }

    @Test
    void listarAutomoveis() throws Exception {
        AutomovelDTO automovel1 = new AutomovelDTO();
        automovel1.setId(1L);
        automovel1.setMarca("Ford");
        automovel1.setModelo("Fiesta");
        automovel1.setAno(2020);

        AutomovelDTO automovel2 = new AutomovelDTO();
        automovel2.setId(2L);
        automovel2.setMarca("Chevrolet");
        automovel2.setModelo("Onix");
        automovel2.setAno(2021);

        List<AutomovelDTO> automoveis = Arrays.asList(automovel1, automovel2);

        when(automovelService.listarAutomoveis()).thenReturn(automoveis);

        mockMvc.perform(get("/api/automoveis"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].marca").value("Ford"))
            .andExpect(jsonPath("$[0].modelo").value("Fiesta"))
            .andExpect(jsonPath("$[1].id").value(2L))
            .andExpect(jsonPath("$[1].marca").value("Chevrolet"))
            .andExpect(jsonPath("$[1].modelo").value("Onix"));

        verify(automovelService, times(1)).listarAutomoveis();
    }

    @Test
    void buscarAutomovel() throws Exception {
        AutomovelDTO automovel = new AutomovelDTO();
        automovel.setId(1L);
        automovel.setMarca("Ford");
        automovel.setModelo("Fiesta");
        automovel.setAno(2020);

        when(automovelService.buscarAutomovel(1L)).thenReturn(automovel);

        mockMvc.perform(get("/api/automoveis/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.marca").value("Ford"))
            .andExpect(jsonPath("$.modelo").value("Fiesta"));

        verify(automovelService, times(1)).buscarAutomovel(1L);
    }

    @Test
    void adicionarAutomovel() throws Exception {
        AutomovelDTO automovelDTO = new AutomovelDTO();
        automovelDTO.setMarca("Fiat");
        automovelDTO.setModelo("Punto");
        automovelDTO.setAno(2022);

        AutomovelDTO automovelCriado = new AutomovelDTO();
        automovelCriado.setId(3L);
        automovelCriado.setMarca("Fiat");
        automovelCriado.setModelo("Punto");
        automovelCriado.setAno(2022);

        when(automovelService.adicionarAutomovel(any(AutomovelDTO.class))).thenReturn(automovelCriado);

        mockMvc.perform(post("/api/automoveis")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"marca\":\"Fiat\",\"modelo\":\"Punto\",\"ano\":2022}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(3L))
            .andExpect(jsonPath("$.marca").value("Fiat"))
            .andExpect(jsonPath("$.modelo").value("Punto"));

        verify(automovelService, times(1)).adicionarAutomovel(any(AutomovelDTO.class));
    }

    @Test
    void atualizarAutomovel() throws Exception {
        AutomovelDTO automovelAtualizado = new AutomovelDTO();
        automovelAtualizado.setId(1L);
        automovelAtualizado.setMarca("Ford");
        automovelAtualizado.setModelo("Fiesta");
        automovelAtualizado.setAno(2021);

        when(automovelService.atualizarAutomovel(eq(1L), any(AutomovelDTO.class))).thenReturn(automovelAtualizado);

        mockMvc.perform(put("/api/automoveis/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"marca\":\"Ford\",\"modelo\":\"Fiesta\",\"ano\":2021}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.ano").value(2021));

        verify(automovelService, times(1)).atualizarAutomovel(eq(1L), any(AutomovelDTO.class));
    }

    @Test
    void deletarAutomovel() throws Exception {
        doNothing().when(automovelService).deletarAutomovel(1L);

        mockMvc.perform(delete("/api/automoveis/1"))
            .andExpect(status().isOk());

        verify(automovelService, times(1)).deletarAutomovel(1L);
    }
}
