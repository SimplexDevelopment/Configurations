package io.github.simplexdev.configurations;

import io.github.simplexdev.configurations.api.IGroup;
import io.github.simplexdev.configurations.api.INode;
import io.github.simplexdev.configurations.api.ISection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Section implements ISection {
    private static final List<Node<?>> ungroupedNodes = new ArrayList<>();
    private final String name;
    private final List<IGroup> groupList;

    public Section(String name) {
        this.name = name;
        this.groupList = new ArrayList<>();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    @Nullable
    public INode<?> getNode(String name) {
        for (INode<?> nodes : ungroupedNodes) {
            if (nodes.getName().equalsIgnoreCase(name)) {
                return nodes;
            }
        }
        return null;
    }

    @Override
    public <T> void setNode(String name, T value) {
        ungroupedNodes.forEach(a -> {
            if (a.getName().equalsIgnoreCase(name)) {
                ungroupedNodes.remove(a);
            }
            ungroupedNodes.add(new Node<>(name, value));
        });
    }

    @Override
    public @Nullable IGroup getGroup(String name) {
        for (IGroup group : groupList) {
            if (group.getName().equalsIgnoreCase(name)) {
                return group;
            } else if (group.hasNest()) {
                for (IGroup nested : group.getNest()) {
                    if (nested.getName().equalsIgnoreCase(name)) {
                        return nested;
                    }
                }
            }
        }
        return null;
    }

    public void addGroup(IGroup group) {
        groupList.add(group);
    }

    @Override
    public String serialize() {
        return getName() + ": " + newLine();
    }

    @Override
    public @Nullable ISection deserialize(String serializedInput) {
        for (ISection section : Configurations.availableSections) {
            if (section.getName().equalsIgnoreCase(serializedInput)) {
                return section;
            }
        }
        return null;
    }

    @Override
    public String indent(int level) {
        StringBuilder sb = new StringBuilder();
        int x = 0;
        while (x < level) {
            sb.append("\\ ");
            x++;
        }
        return sb.toString();
    }

    @Override
    public String newLine() {
        return "\n";
    }
}
