package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class BuffetTest {

    @Test
    public void testBuffetGettersAndSetters() {
        // Dados de exemplo
        String nome = "Buffet Exemplo";
        String cnpj = "00.000.000/0000-00";
        String email = "buffet@example.com";
        String senha = "senha123";
        String endereco = "Rua Exemplo, 123";
        String horarioFuncionamento = "09:00 - 18:00";
        String descricao = "Descrição do Buffet";
        List<String> datasDisponiveis = Arrays.asList("01/01/2024", "02/01/2024");
        List<String> horariosDisponiveis = Arrays.asList("10:00", "14:00");

        // Criação do objeto Buffet
        Buffet buffet = new Buffet(nome, cnpj, email, senha, endereco, horarioFuncionamento, descricao);

        // Teste dos getters
        assertEquals(nome, buffet.getNome());
        assertEquals(cnpj, buffet.getCnpj());
        assertEquals(email, buffet.getEmail());
        assertEquals(senha, buffet.getSenha());
        assertEquals(endereco, buffet.getEndereco());
        assertEquals(horarioFuncionamento, buffet.getHorarioFuncionamento());
        assertEquals(descricao, buffet.getDescricao());

        // Teste dos setters
        buffet.setNome("Novo Nome");
        assertEquals("Novo Nome", buffet.getNome());

        buffet.setCnpj("11.111.111/1111-11");
        assertEquals("11.111.111/1111-11", buffet.getCnpj());

        buffet.setEmail("novoemail@example.com");
        assertEquals("novoemail@example.com", buffet.getEmail());

        buffet.setSenha("novaSenha123");
        assertEquals("novaSenha123", buffet.getSenha());

        buffet.setEndereco("Nova Rua, 456");
        assertEquals("Nova Rua, 456", buffet.getEndereco());

        buffet.setHorarioFuncionamento("10:00 - 20:00");
        assertEquals("10:00 - 20:00", buffet.getHorarioFuncionamento());

        buffet.setDescricao("Nova Descrição");
        assertEquals("Nova Descrição", buffet.getDescricao());

        buffet.setDatasDisponiveis(datasDisponiveis);
        assertEquals(datasDisponiveis, buffet.getDatasDisponiveis());

        buffet.setHorariosDisponiveis(horariosDisponiveis);
        assertEquals(horariosDisponiveis, buffet.getHorariosDisponiveis());
    }
}
