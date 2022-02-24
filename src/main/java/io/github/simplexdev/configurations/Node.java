package io.github.simplexdev.configurations;

import io.github.simplexdev.configurations.api.INode;

public class Node<T> implements INode<T> {
    private final String name;
    private final T value;

    /**
     * @param name  The name of the entry.
     * @param value The value of the entry
     */
    public Node(String name, T value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return The name of the entry.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return The value of the entry.
     */
    @Override
    public T getValue() {
        return value;
    }

    @Override
    public String serialize() {
        return (getName() + ": " + getValue().toString());
    }

    // I think this might work
    @Override
    public INode deserialize(String serializedInput) {
        String[] split = serializedInput.split(":\t");
        String name = split[0].trim();
        String value = split[1].trim();
        Utils utils = new Utils();

        if (utils.checkForBoolean(value)) return new Node<>(name, Boolean.parseBoolean(value));
        if (utils.checkForFloat(value)) return new Node<>(name, Float.parseFloat(value));
        if (utils.checkForInt(value)) return new Node<>(name, Integer.parseInt(value));
        if (utils.checkForLong(value)) return new Node<>(name, Long.parseLong(value));
        if (utils.checkForDouble(value)) return new Node<>(name, Double.parseDouble(value));
        return new Node<>(name, value);
    }
}
