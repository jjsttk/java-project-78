import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorNumberSchemaTest {
    private Validator v;

    @BeforeEach
    void setUp() {
        v = new Validator();
    }

    @Test
    public void isValid() {
        var schema = v.number();
        assertTrue(schema.isValid(5)); // true
        // Пока не вызван метод required(), null считается валидным
        assertTrue(schema.isValid(null)); // true
        assertTrue(schema.positive().isValid(null)); // true
    }

    @Test
    public void isValidWithRequiredAndPositive() {
        var schema = v.number();
        schema.required();
        schema.positive();
        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(10)); // true
        // Потому что ранее мы вызвали метод positive()
        assertFalse(schema.isValid(-10)); // false
        //  Ноль — не положительное число
        assertFalse(schema.isValid(0)); // false
    }

    @Test
    public void isValidWithRequiredAndRange() {
        var schema = v.number();
        schema.required();
        schema.range(5, 10);
        assertTrue(schema.isValid(5)); // true
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid(4)); // false
        assertFalse(schema.isValid(11)); // false
        assertFalse(schema.isValid(-5)); // false
    }
}
