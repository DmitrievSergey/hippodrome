import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTests {
    @Test
    void firstConstructorParameterCannotBeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5, 5));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
}
