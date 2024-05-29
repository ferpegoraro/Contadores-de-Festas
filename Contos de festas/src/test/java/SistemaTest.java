import entity.Agendamento;
import entity.Buffet;
import entity.Cliente;
import control.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

public class SistemaTest {

    private Sistema sistema;

    @BeforeEach
    public void setUp() {
        sistema = new Sistema();
    }

    @Test
    public void testCadastrarCliente() {
        assertTrue(sistema.cadastrarCliente("Novo Cliente", "123.456.789-10", "novo_cliente@example.com", "senha123"));
        assertFalse(sistema.cadastrarCliente("Calebe", "xxx.xxx.xxx-xx", "calebe@mackenzie.com", "123"));
    }

    @Test
    public void testCadastrarBuffet() {
        assertTrue(sistema.cadastrarBuffet("Novo Buffet", "xx.xxx.xxx/0001-xx", "novo_buffet@example.com", "senha123", "Endereço Buffet", "08:00 - 18:00", "Descrição Buffet"));
        assertFalse(sistema.cadastrarBuffet("Cedrom", "xx.xxx.xxx/0001-xx", "cedrom@gmail.com", "123", "Endereço exemplo", "12:00 - 17:00 e 19:00 - 00:00", "Buffet de exemplo"));
    }

    @Test
    public void testLoginCliente() {
        Cliente cliente = sistema.loginCliente("calebe@mackenzie.com", "123");
        assertNotNull(cliente);
        assertEquals("Calebe", cliente.getNomeCompleto());

        Cliente clienteInvalido = sistema.loginCliente("email_invalido@example.com", "senha_invalida");
        assertNull(clienteInvalido);
    }

    @Test
    public void testLoginBuffet() {
        Buffet buffet = sistema.loginBuffet("cedrom@gmail.com", "123");
        assertNotNull(buffet);
        assertEquals("Cedrom", buffet.getNome());

        Buffet buffetInvalido = sistema.loginBuffet("email_invalido@example.com", "senha_invalida");
        assertNull(buffetInvalido);
    }

    @Test
    public void testAgendarBuffet() {
        Cliente cliente = new Cliente("Novo Cliente", "123.456.789-10", "novo_cliente@example.com", "senha123");
        Buffet buffet = new Buffet("Novo Buffet", "xx.xxx.xxx/0001-xx", "novo_buffet@example.com", "senha123", "Endereço Buffet", "08:00 - 18:00", "Descrição Buffet");
        Date data = new Date();
        String horario = "14:00";

        assertTrue(sistema.agendarBuffet(cliente, buffet, horario, data));

        List<Agendamento> agendamentosCliente = sistema.getAgendamentosCliente(cliente);
        assertEquals(1, agendamentosCliente.size());
        assertEquals(buffet, agendamentosCliente.get(0).getBuffet());
        assertEquals(data, agendamentosCliente.get(0).getData());
        assertEquals(horario, agendamentosCliente.get(0).getHorario());
    }

    @Test
    public void testCancelarAgendamento() {
        Cliente cliente = new Cliente("Novo Cliente", "123.456.789-10", "novo_cliente@example.com", "senha123");
        Buffet buffet = new Buffet("Novo Buffet", "xx.xxx.xxx/0001-xx", "novo_buffet@example.com", "senha123", "Endereço Buffet", "08:00 - 18:00", "Descrição Buffet");
        Date data = new Date();
        String horario = "14:00";

        sistema.agendarBuffet(cliente, buffet, horario, data);
        List<Agendamento> agendamentosCliente = sistema.getAgendamentosCliente(cliente);
        assertEquals(1, agendamentosCliente.size());

        assertTrue(sistema.cancelarAgendamento(agendamentosCliente.get(0)));
        assertEquals(0, sistema.getAgendamentosCliente(cliente).size());
    }

    @Test
    public void testGetBuffets() {
        List<Buffet> buffets = sistema.getBuffets();
        assertEquals(1, buffets.size());
        assertEquals("Cedrom", buffets.get(0).getNome());
    }
}
