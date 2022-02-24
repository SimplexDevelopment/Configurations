package io.github.simplexdev.configurations.api;

public interface INode<T> extends Serializable<INode<T>> {
    /**
     * @return The name of the configuration entry.
     */
    String getName();

    /**
     * @return The value of the configuration entry.
     */
    T getValue();
}
