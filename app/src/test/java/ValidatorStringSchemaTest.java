import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorStringSchemaTest {
    private Validator v;

    @BeforeEach
    void setUp() {
        v = new Validator();
    }
    @Test
    public void isValid() {
        var schema = v.string();
        // Пока не вызван метод required(), null и пустая строка считаются валидным
        assertTrue(schema.isValid("")); // true
        assertTrue(schema.isValid(null)); // true
        assertTrue(schema.isValid(" ")); // true
    }
    @Test
    public void isValidWithRequired() {
        var schema = v.string();
        // Добавляем проверку обязательного заполнения
        schema.required();
        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("")); // false
        assertTrue(schema.isValid("what does the fox say")); // true
        assertTrue(schema.isValid("hexlet")); // true
    }

    @Test
    public void isValidWithRequiredAndContains() {
        var schema = v.string();
        schema.required();
        assertTrue(schema.contains("wh").isValid("what does the fox say")); // true
        assertTrue(schema.contains("what").isValid("what does the fox say")); // true
        assertFalse(schema.contains("whatthe").isValid("what does the fox say")); // false
        // правило contains активно при следующем вызове проверки
        assertFalse(schema.isValid("what does the fox say")); // false
    }

    @Test
    public void isValidDoubleCall() {
        // Если один валидатор вызывался несколько раз
        // то последний имеет приоритет (перетирает предыдущий)
        var schema1 = v.string();
        assertTrue(schema1.minLength(10).minLength(4).isValid("Hexlet")); // true
    }
}
