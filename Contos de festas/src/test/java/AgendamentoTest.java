package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class AgendamentoTest {

    @Test
    public void testAgendamentoGettersAndSetters() {
        // Dados de exemplo
        Cliente cliente = new Cliente("Nome Cliente", "123.456.789-10", "cliente@example.com", "senha");
        Buffet buffet = new Buffet("Nome Buffet", "123456789", "buffet@example.com", "senha", "Endereço Buffet", "08:00 - 18:00", "Descrição Buffet");
        Date data = new Date();
        String horario = "14:00";

        // Criação do objeto Agendamento
        Agendamento agendamento = new Agendamento(cliente, buffet, data, horario);

        // Teste dos getters e setters
        assertEquals(cliente, agendamento.getCliente());
        assertEquals(buffet, agendamento.getBuffet());
        assertEquals(data, agendamento.getData());
        assertEquals(horario, agendamento.getHorario());

        agendamento.setCliente(null);
        assertNull(agendamento.getCliente());

        agendamento.setBuffet(null);
        assertNull(agendamento.getBuffet());

        Date novaData = new Date();
        agendamento.setData(novaData);
        assertEquals(novaData, agendamento.getData());

        String novoHorario = "16:00";
        agendamento.setHorario(novoHorario);
        assertEquals(novoHorario, agendamento.getHorario());
    }

    @Test
    public void testAgendamentoPagamento() {
        // Dados de exemplo
        Cliente cliente = new Cliente("Nome Cliente", "123.456.789-10", "cliente@example.com", "senha");
        Buffet buffet = new Buffet("Nome Buffet", "123456789", "buffet@example.com", "senha", "Endereço Buffet", "08:00 - 18:00", "Descrição Buffet");
        Date data = new Date();
        String horario = "14:00";

        // Criação do objeto Agendamento
        Agendamento agendamento = new Agendamento(cliente, buffet, data, horario);

        // Teste do pagamento inicial
        assertFalse(agendamento.isPago());

        // Teste do pagamento após definir como pago
        agendamento.setPago(true);
        assertTrue(agendamento.isPago());
    }
}
