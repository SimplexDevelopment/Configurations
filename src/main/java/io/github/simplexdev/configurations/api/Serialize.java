package io.github.simplexdev.configurations.api;

public interface Serialize<T> {
    String serialize();

    T deserialize(String serializedInput);
}
