package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> rules) {
        addCheck("shape", value -> {
            if (value == null) {
                return true; // Если значение null, оно считается валидным
            }

            for (var entry : rules.entrySet()) {
                String key = entry.getKey();
                // Само значение - это схема проверки
                BaseSchema<String> schema = entry.getValue();

                // Если ключ отсутствует в мапе, проверка проваливается
                if (!value.containsKey(key)) {
                    return false;
                }

                // Проверяем значение ключа через соответствующую схему
                if (!schema.isValid(value.get(key))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

}
