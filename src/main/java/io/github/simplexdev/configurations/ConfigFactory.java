package io.github.simplexdev.configurations;

import io.github.simplexdev.configurations.api.INode;
import io.github.simplexdev.configurations.api.ISection;

public class ConfigFactory {
    public ISection createNewSection(String name) {
        ISection section = new Section(name);
        Configurations.availableSections.add(section);
        return section;
    }

    public INode<String> stringNode(String name, String value) {
        return new Node<>(name, value);
    }

    public INode<Boolean> booleanNode(String name, boolean value) {
        return new Node<>(name, value);
    }

    public INode<Long> longNode(String name, long value) {
        return new Node<>(name, value);
    }

    public INode<Float> floatNode(String name, float value) {
        return new Node<>(name, value);
    }

    public INode<Integer> integerNode(String name, int value) {
        return new Node<>(name, value);
    }

    public INode<Double> doubleNode(String name, Double value) {
        return new Node<>(name, value);
    }
}
