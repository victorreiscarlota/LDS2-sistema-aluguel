package aracar;
import aracar.Controller.ContratoController;
import aracar.DTO.ContratoDTO;
import aracar.Service.ContratoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
class ContratoControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ContratoService contratoService;
    @InjectMocks
    private ContratoController contratoController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contratoController).build();
    }
    @Test
    void listarContratos() throws Exception {
        ContratoDTO contrato1 = new ContratoDTO();
        contrato1.setId(1L);
        contrato1.setDataContrato(new Date());
        contrato1.setTipo("Locação");
        ContratoDTO contrato2 = new ContratoDTO();
        contrato2.setId(2L);
        contrato2.setDataContrato(new Date());
        contrato2.setTipo("Venda");
        List<ContratoDTO> contratos = Arrays.asList(contrato1, contrato2);
        when(contratoService.listarContratos()).thenReturn(contratos);
        mockMvc.perform(get("/api/contratos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].tipo").value("Locação"))
            .andExpect(jsonPath("$[1].id").value(2L))
            .andExpect(jsonPath("$[1].tipo").value("Venda"));
        verify(contratoService, times(1)).listarContratos();
    }
    @Test
    void buscarContrato() throws Exception {
        ContratoDTO contrato = new ContratoDTO();
        contrato.setId(1L);
        contrato.setDataContrato(new Date());
        contrato.setTipo("Locação");
        when(contratoService.buscarContrato(1L)).thenReturn(contrato);
        mockMvc.perform(get("/api/contratos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.tipo").value("Locação"));
        verify(contratoService, times(1)).buscarContrato(1L);
    }
    @Test
    void adicionarContrato() throws Exception {
        ContratoDTO contratoDTO = new ContratoDTO();
        contratoDTO.setDataContrato(new Date());
        contratoDTO.setTipo("Locação");
        ContratoDTO contratoCriado = new ContratoDTO();
        contratoCriado.setId(3L);
        contratoCriado.setDataContrato(contratoDTO.getDataContrato());
        contratoCriado.setTipo(contratoDTO.getTipo());
        when(contratoService.adicionarContrato(any(ContratoDTO.class))).thenReturn(contratoCriado);
        mockMvc.perform(post("/api/contratos")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"dataContrato\":\"2024-10-01\",\"tipo\":\"Locação\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(3L))
            .andExpect(jsonPath("$.tipo").value("Locação"));
        verify(contratoService, times(1)).adicionarContrato(any(ContratoDTO.class));
    }
    @Test
    void atualizarContrato() throws Exception {
        ContratoDTO contratoAtualizado = new ContratoDTO();
        contratoAtualizado.setId(1L);
        contratoAtualizado.setDataContrato(new Date());
        contratoAtualizado.setTipo("Venda");
        when(contratoService.atualizarContrato(eq(1L), any(ContratoDTO.class))).thenReturn(contratoAtualizado);
        mockMvc.perform(put("/api/contratos/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"dataContrato\":\"2024-10-01\",\"tipo\":\"Venda\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.tipo").value("Venda"));
        verify(contratoService, times(1)).atualizarContrato(eq(1L), any(ContratoDTO.class));
    }
    @Test
    void deletarContrato() throws Exception {
        doNothing().when(contratoService).deletarContrato(1L);
        mockMvc.perform(delete("/api/contratos/1"))
            .andExpect(status().isOk());
        verify(contratoService, times(1)).deletarContrato(1L);
    }
}