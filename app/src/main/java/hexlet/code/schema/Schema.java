package hexlet.code.schema;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class Schema {
    private final Map<String, Predicate<Object>> rules = new HashMap<>();

    /**
     * Добавляет новое правило проверки.
     * @param key имя правила
     * @param rule правило в виде предиката
     */
    public void addCheck(String key, Predicate<Object> rule) {
        rules.put(key, rule);
    }

    /**
     * Проверяет значение на соответствие всем добавленным правилам.
     * @param value проверяемое значение
     * @return true, если значение прошло все проверки
     */
    public boolean isValid(Object value) {
        for (var rule : rules.values()) {
            if (!rule.test(value)) {
                return false; // Если хотя бы одна проверка не пройдена, возвращаем false
            }
        }
        return true; // Все проверки успешны
    }
}
