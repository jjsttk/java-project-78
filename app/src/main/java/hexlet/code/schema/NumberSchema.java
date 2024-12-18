package hexlet.code.schema;

public final class NumberSchema extends BaseSchema<Number> {

    public NumberSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value == null || value.doubleValue() > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", value -> value != null && value.intValue() >= min && value.intValue() <= max);
        return this;
    }
}
