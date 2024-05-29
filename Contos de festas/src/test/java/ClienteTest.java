package entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("João da Silva", "123.456.789-00", "joao@example.com", "senha123");
    }

    @Test
    public void testGetNomeCompleto() {
        assertEquals("João da Silva", cliente.getNomeCompleto());
    }

    @Test
    public void testSetNomeCompleto() {
        cliente.setNomeCompleto("Maria da Silva");
        assertEquals("Maria da Silva", cliente.getNomeCompleto());
    }

    @Test
    public void testGetCpf() {
        assertEquals("123.456.789-00", cliente.getCpf());
    }

    @Test
    public void testSetCpf() {
        cliente.setCpf("987.654.321-00");
        assertEquals("987.654.321-00", cliente.getCpf());
    }

    @Test
    public void testGetEmail() {
        assertEquals("joao@example.com", cliente.getEmail());
    }

    @Test
    public void testSetEmail() {
        cliente.setEmail("maria@example.com");
        assertEquals("maria@example.com", cliente.getEmail());
    }

    @Test
    public void testGetSenha() {
        assertEquals("senha123", cliente.getSenha());
    }

    @Test
    public void testSetSenha() {
        cliente.setSenha("novaSenha123");
        assertEquals("novaSenha123", cliente.getSenha());
    }
}
