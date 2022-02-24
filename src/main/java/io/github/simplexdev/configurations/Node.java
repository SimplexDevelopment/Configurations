package io.github.simplexdev.configurations;

import io.github.simplexdev.configurations.api.INode;

public class Node<T> implements INode<T> {
    private final String name;
    private final T value;

    /**
     * @param name The name of the entry.
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

    // Not sure how to deserialize here.
    @Override
    public INode<T> deserialize(String serializedInput) {
        return null;
    }
}
