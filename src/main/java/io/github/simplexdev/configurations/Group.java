package io.github.simplexdev.configurations;

import io.github.simplexdev.configurations.api.IGroup;
import io.github.simplexdev.configurations.api.INode;
import io.github.simplexdev.configurations.api.ISection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Group implements IGroup {
    private final List<IGroup> nestedGroups = new ArrayList<>();
    private final List<INode<?>> nodes = new ArrayList<>();
    private final String name;
    private final ISection section;
    private IGroup group;

    public Group(ISection section, String name, INode<?>... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
        this.section = section;
        this.name = name;
    }

    public Group(ISection section, String name) {
        this.section = section;
        this.name = name;
    }

    public Group(ISection section, IGroup group, String name) {
        this.section = section;
        this.group = group;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public IGroup createGroup(String name, INode<?>... nodes) {
        IGroup group = new Group(section, name, nodes);
        nestedGroups.add(group);
        return group;
    }

    /**
     * Creates a group as a child of this group.
     * @param name The name of the nested group.
     * @return The nested group.
     */
    public IGroup emptyGroup(String name) {
        IGroup group = new Group(section, name);
        nestedGroups.add(group);
        return group;
    }

    /**
     * Gets a nested group.
     * Can return null if no nested group exists.
     * @param name The name of the nested group
     * @return The nested group.
     */
    @Override
    public @Nullable IGroup getGroup(String name) {
        for (IGroup nested : nestedGroups) {
            if (nested.getName().equalsIgnoreCase(name)) {
                return nested;
            }
        }
        return null;
    }

    @Override
    public @NotNull ISection getSection() {
        return section;
    }

    @Override
    public @Nullable Collection<? extends INode<?>> getNodes() {
        return nodes;
    }

    @Override
    public IGroup addNodes(INode<?>... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
        return this;
    }

    @Override
    public IGroup removeNodes(INode<?>... nodes) {
        this.nodes.removeAll(Arrays.asList(nodes));
        return this;
    }

    @Override
    public IGroup addNode(INode<?> node) {
        nodes.add(node);
        return this;
    }

    @Override
    public IGroup removeNode(INode<?> node) {
        nodes.remove(node);
        return this;
    }

    @Override
    public String serialize() {
        return getName();
    }

    @Override
    public IGroup deserialize(String serializedInput) {
        return getSection().getGroup(serializedInput);
    }
}
