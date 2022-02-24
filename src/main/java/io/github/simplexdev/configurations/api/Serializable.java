package io.github.simplexdev.configurations.api;

public interface Serializable<T> {
    String serialize();
    T deserialize(String serializedInput);
}
