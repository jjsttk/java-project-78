import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

public class ValidatorMapSchemaTest {
    private Validator v;

    @BeforeEach
    public void setUp() {
        v = new Validator();
    }

    @Test
    public void isValid() {
        var schema = v.map();
        assertTrue(schema.isValid(null)); // true
        schema.required();
        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(new HashMap<>())); // true

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true
    }

    @Test
    public void isValidWithRequiredAndSizeof() {
        var schema = v.map();
        schema.required();
        schema.sizeof(2);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));  // false
        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // true
    }
}
