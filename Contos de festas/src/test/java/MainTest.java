import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testMain() {
        // Para testar o método main, podemos apenas garantir que ele não lança exceções
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
