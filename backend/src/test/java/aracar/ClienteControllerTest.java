package aracar;

import aracar.Controller.ClienteController;
import aracar.DTO.ClienteDTO;
import aracar.Service.ClienteService;
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

class ClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void listarClientes() throws Exception {
        ClienteDTO cliente1 = new ClienteDTO();
        cliente1.setId(1L);
        cliente1.setNome("João");
        cliente1.setRg("123456789");
        cliente1.setCpf("11122233344");

        ClienteDTO cliente2 = new ClienteDTO();
        cliente2.setId(2L);
        cliente2.setNome("Maria");
        cliente2.setRg("987654321");
        cliente2.setCpf("55566677788");

        List<ClienteDTO> clientes = Arrays.asList(cliente1, cliente2);

        when(clienteService.listarClientes()).thenReturn(clientes);

        mockMvc.perform(get("/api/clientes"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].nome").value("João"))
            .andExpect(jsonPath("$[1].id").value(2L))
            .andExpect(jsonPath("$[1].nome").value("Maria"));

        verify(clienteService, times(1)).listarClientes();
    }

    @Test
    void buscarCliente() throws Exception {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setRg("123456789");
        cliente.setCpf("11122233344");

        when(clienteService.buscarCliente(1L)).thenReturn(cliente);

        mockMvc.perform(get("/api/clientes/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.nome").value("João"))
            .andExpect(jsonPath("$.rg").value("123456789"))
            .andExpect(jsonPath("$.cpf").value("11122233344"));

        verify(clienteService, times(1)).buscarCliente(1L);
    }

    @Test
    void adicionarCliente() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Carlos");
        clienteDTO.setRg("456123789");
        clienteDTO.setCpf("22233344455");

        ClienteDTO clienteCriado = new ClienteDTO();
        clienteCriado.setId(3L);
        clienteCriado.setNome("Carlos");
        clienteCriado.setRg("456123789");
        clienteCriado.setCpf("22233344455");

        when(clienteService.adicionarCliente(any(ClienteDTO.class))).thenReturn(clienteCriado);

        mockMvc.perform(post("/api/clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"nome\":\"Carlos\",\"rg\":\"456123789\",\"cpf\":\"22233344455\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(3L))
            .andExpect(jsonPath("$.nome").value("Carlos"));

        verify(clienteService, times(1)).adicionarCliente(any(ClienteDTO.class));
    }

    @Test
    void atualizarCliente() throws Exception {
        ClienteDTO clienteAtualizado = new ClienteDTO();
        clienteAtualizado.setId(1L);
        clienteAtualizado.setNome("João Atualizado");
        clienteAtualizado.setRg("123456789");
        clienteAtualizado.setCpf("11122233344");

        when(clienteService.atualizarCliente(eq(1L), any(ClienteDTO.class))).thenReturn(clienteAtualizado);

        mockMvc.perform(put("/api/clientes/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"nome\":\"João Atualizado\",\"rg\":\"123456789\",\"cpf\":\"11122233344\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.nome").value("João Atualizado"));

        verify(clienteService, times(1)).atualizarCliente(eq(1L), any(ClienteDTO.class));
    }

    @Test
    void deletarCliente() throws Exception {
        doNothing().when(clienteService).deletarCliente(1L);

        mockMvc.perform(delete("/api/clientes/1"))
            .andExpect(status().isOk());

        verify(clienteService, times(1)).deletarCliente(1L);
    }
}
