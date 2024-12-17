package hexlet.code;

import hexlet.code.schema.StringSchema;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Validator {

    public StringSchema string() {
        return new StringSchema();
    }
}
