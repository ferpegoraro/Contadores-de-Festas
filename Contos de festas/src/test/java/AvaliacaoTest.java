package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AvaliacaoTest {

    @Test
    public void testAvaliacaoGettersAndSetters() {
        // Dados de exemplo
        String buffetName = "Buffet Exemplo";
        String clientName = "Cliente Exemplo";
        int rating = 5;

        // Criação do objeto Avaliacao
        Avaliacao avaliacao = new Avaliacao();

        // Teste dos setters
        avaliacao.setBuffetName(buffetName);
        avaliacao.setClientName(clientName);
        avaliacao.setRating(rating);

        // Teste dos getters
        assertEquals(buffetName, avaliacao.getBuffetName());
        assertEquals(clientName, avaliacao.getClientName());
        assertEquals(rating, avaliacao.getRating());
    }

    @Test
    public void testAvaliacaoToString() {
        // Dados de exemplo
        String buffetName = "Buffet Exemplo";
        String clientName = "Cliente Exemplo";
        int rating = 5;

        // Criação do objeto Avaliacao
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setBuffetName(buffetName);
        avaliacao.setClientName(clientName);
        avaliacao.setRating(rating);

        // Teste do método toString
        String expectedToString = "Review{buffetName='" + buffetName + "', clientName='" + clientName + "', rating=" + rating + "}";
        assertEquals(expectedToString, avaliacao.toString());
    }
}
