package utils;

import java.util.function.Function;

public enum TypeUtil {
    INTEGER(Integer::valueOf, Integer.class.getName()),
    LONG(Long::valueOf, Long.class.getName()),
    DOUBLE(Double::valueOf, Double.class.getName()),
    STRING(String::valueOf, String.class.getName());

    private final Function<String, Object> convertOperation;
    private final String typeName;

    TypeUtil(Function<String, Object> convertOperation, String typeName) {
        this.convertOperation = convertOperation;
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }

    public Object getValue(String string) {
        return convertOperation.apply(string);
    }

}

