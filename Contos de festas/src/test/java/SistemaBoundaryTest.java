import control.Sistema;
import entity.Cliente;
import entity.Buffet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaBoundaryTest {

    private SistemaBoundary sistemaBoundary;
    private InputStream stdin;

    @BeforeEach
    public void setUp() {
        sistemaBoundary = new SistemaBoundary();
        stdin = System.in;
    }

    @Test
    public void testLoginCliente() {
        String input = "1\n" + "calebe@mackenzie.com\n" + "123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        sistemaBoundary.iniciar();
        Cliente cliente = new Cliente("Calebe", "xxx.xxx.xxx-xx", "calebe@mackenzie.com", "123");
        assertEquals(cliente.getEmail(), sistemaBoundary.sistema.getClientes().get(0).getEmail());
    }

    @Test
    public void testLoginBuffet() {
        String input = "1\n" + "cedrom@gmail.com\n" + "123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        sistemaBoundary.iniciar();
        Buffet buffet = new Buffet("Cedrom", "xx.xxx.xxx/0001-xx", "cedrom@gmail.com", "123", "Endereço exemplo", "12:00 - 17:00 e 19:00 - 00:00", "Buffet de exemplo");
        assertEquals(buffet.getEmail(), sistemaBoundary.sistema.getBuffets().get(0).getEmail());
    }

    @Test
    public void testCadastroCliente() {
        String input = "2\n" + "1\n" + "João\n" + "xxx.xxx.xxx-xx\n" + "joao@gmail.com\n" + "123\n" + "123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        sistemaBoundary.iniciar();
        assertEquals("joao@gmail.com", sistemaBoundary.sistema.getClientes().get(1).getEmail());
    }

    @Test
    public void testCadastroBuffet() {
        String input = "2\n" + "2\n" + "Buffet 2\n" + "yy.yyy.yyy/0002-yy\n" + "buffet2@gmail.com\n" + "123\n" + "123\n" + "Endereço buffet 2\n" + "12:00 - 17:00 e 19:00 - 00:00\n" + "Descrição buffet 2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        sistemaBoundary.iniciar();
        assertEquals("buffet2@gmail.com", sistemaBoundary.sistema.getBuffets().get(1).getEmail());
    }

    @Test
    public void testNovoAgendamento() {
        Cliente cliente = new Cliente("Calebe", "xxx.xxx.xxx-xx", "calebe@mackenzie.com", "123");
        Buffet buffet = sistemaBoundary.sistema.getBuffets().get(0);
        String input = "1\n" + "1\n" + "1\n" + "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        sistemaBoundary.novoAgendamento(cliente);
        assertEquals(1, sistemaBoundary.sistema.getAgendamentos().size());
    }

    @Test
    public void testMeusAgendamentos() {
        Cliente cliente = new Cliente("Calebe", "xxx.xxx.xxx-xx", "calebe@mackenzie.com", "123");
        sistemaBoundary.sistema.agendarBuffet(cliente, sistemaBoundary.sistema.getBuffets().get(0), "12:00 às 17:00", new Date());
        String expectedOutput = "Meus Agendamentos";
        assertTrue(sistemaBoundary.sistema.getAgendamentosCliente(cliente).size() > 0);
    }

    @Test
    public void testCancelarAgendamento() {
        Cliente cliente = new Cliente("Calebe", "xxx.xxx.xxx-xx", "calebe@mackenzie.com", "123");
        sistemaBoundary.sistema.agendarBuffet(cliente, sistemaBoundary.sistema.getBuffets().get(0), "12:00 às 17:00", new Date());
        sistemaBoundary.cancelarAgendamento(cliente);
        assertEquals(0, sistemaBoundary.sistema.getAgendamentosCliente(cliente).size());
    }

    @Test
    public void testAvaliarBuffet() {
        Cliente cliente = new Cliente("Calebe", "xxx.xxx.xxx-xx", "calebe@mackenzie.com", "123");
        Buffet buffet = sistemaBoundary.sistema.getBuffets().get(0);
        sistemaBoundary.sistema.agendarBuffet(cliente, buffet, "12:00 às 17:00", new Date());
        String input = "1\n" + "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        sistemaBoundary.avaliarBuffet(cliente);
        assertEquals(5, sistemaBoundary.sistema.getAgendamentosCliente(cliente).get(0).getRating());
    }

    @Test
    public void testVerSolicitacoesReserva() {
        Buffet buffet = sistemaBoundary.sistema.getBuffets().get(0);
        Cliente cliente = new Cliente("Calebe", "xxx.xxx.xxx-xx", "calebe@mackenzie.com", "123");
        sistemaBoundary.sistema.agendarBuffet(cliente, buffet, "12:00 às 17:00", new Date());
        String expectedOutput = "Solicitações de Reserva\n" +
                "Cliente: Calebe, Data: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + ", Horário: 12:00 às 17:00\n";
        assertEquals(expectedOutput, getOutputFromMethod(() -> sistemaBoundary.verSolicitacoesReserva(buffet)));
    }

    @Test
    public void testVerAgendamentos() {
        Buffet buffet = sistemaBoundary.sistema.getBuffets().get(0);
        Cliente cliente = new Cliente("Calebe", "xxx.xxx.xxx-xx", "calebe@mackenzie.com", "123");
        sistemaBoundary.sistema.agendarBuffet(cliente, buffet, "12:00 às 17:00", new Date());
        String expectedOutput = "Agendamentos\n" +
                "Cliente: Calebe, Data: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + ", Horário: 12:00 às 17:00\n";
        assertEquals(expectedOutput, getOutputFromMethod(() -> sistemaBoundary.verAgendamentos(buffet)));
    }

    @Test
    public void testCancelarAgendamentoBuffet() {
        Buffet buffet = sistemaBoundary.sistema.getBuffets().get(0);
        Cliente cliente = new Cliente("Calebe", "xxx.xxx.xxx-xx", "calebe@mackenzie.com", "123");
        sistemaBoundary.sistema.agendarBuffet(cliente, buffet, "12:00 às 17:00", new Date());
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertTrue(sistemaBoundary.sistema.getAgendamentosBuffet(buffet).size() > 0);
        getOutputFromMethod(() -> sistemaBoundary.cancelarAgendamentoBuffet(buffet));
        assertEquals(0, sistemaBoundary.sistema.getAgendamentosBuffet(buffet).size());
    }

    @Test
    public void testAvaliarCliente() {
        Buffet buffet = sistemaBoundary.sistema.getBuffets().get(0);
        Cliente cliente = new Cliente("Calebe", "xxx.xxx.xxx-xx", "calebe@mackenzie.com", "123");
        sistemaBoundary.sistema.agendarBuffet(cliente, buffet, "12:00 às 17:00", new Date());
        String input = "1\n" + "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertEquals(0, sistemaBoundary.sistema.getAgendamentosBuffet(buffet).get(0).getRating());
        getOutputFromMethod(() -> sistemaBoundary.avaliarCliente(buffet));
        assertEquals(5, sistemaBoundary.sistema.getAgendamentosBuffet(buffet).get(0).getRating());
    }

    private String getOutputFromMethod(Runnable method) {
        // Salvar o System.out em um objeto PrintStream
        PrintStream originalOut = System.out;
        // Criar um novo ByteArrayOutputStream para redirecionar o System.out
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        // Redirecionar o System.out para o novo ByteArrayOutputStream
        System.setOut(new PrintStream(newOut));
        // Executar o método que queremos testar
        method.run();
        // Restaurar o System.out
        System.setOut(originalOut);
        // Retornar a saída capturada como uma string
        return newOut.toString();
    }
}
