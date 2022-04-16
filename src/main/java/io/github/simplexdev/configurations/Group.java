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
    private IGroup group = null;

    public Group(ISection section, String name, INode<?>... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
        this.section = section;
        this.name = name;
    }

    /**
     * Initializes this group as an empty group.
     *
     * @param section The parent section for this group.
     * @param name    The name of this group.
     */
    public Group(@NotNull ISection section, @NotNull String name) {
        this.section = section;
        this.name = name;
    }

    /**
     * Initializes this Group as a nested group.
     * It is important to note that the section assigned to this group will be the section assigned to the parent group.
     * If this is a nested group of a nested group, it will search upwards until the parent group is discovered,
     * and the parent section of that group will be assigned.
     *
     * @param group The parent group of this group.
     * @param name  The name of this group.
     */
    public Group(@NotNull IGroup group, @NotNull String name) {
        this.section = group.getParentSection();
        this.group = group;
        this.name = name;
    }

    /**
     * Creates a nested group
     *
     * @param name  The name of the nested group
     * @param nodes The node entries to include in the group
     * @return The created nested group.
     */
    @Override
    public IGroup createNestedGroup(IGroup group, String name, INode<?>... nodes) {
        IGroup f = new Group(this, name);
        nestedGroups.add(f);
        return f;
    }

    /**
     * Creates an empty nested group.
     *
     * @param group The parent group.
     * @param name  The name of the nested group.
     * @return The nested group.
     */
    public IGroup emptyGroup(IGroup group, String name) {
        IGroup f = new Group(group, name);
        nestedGroups.add(group);
        return f;
    }

    /**
     * Gets the parent group. If there is no parent group, this will return null.
     * Please remember to use proper null catching when implementing this.
     *
     * @return The parent group, or null if there is no parent.
     */
    @Override
    @Nullable
    public IGroup getParentGroup() {
        return group;
    }

    /**
     * @return The name of this group.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a nested group.
     * Can return null if no nested group exists.
     *
     * @param name The name of the nested group
     * @return The nested group.
     */
    @Override
    public @Nullable IGroup getNestedGroup(String name) {
        for (IGroup nested : nestedGroups) {
            if (nested.getName().equalsIgnoreCase(name)) {
                return nested;
            }
        }
        return null;
    }

    @Override
    public boolean hasNest() {
        return nestedGroups.isEmpty();
    }

    @Override
    public List<IGroup> getNest() {
        return nestedGroups;
    }

    /**
     * Gets the parent section associated with this group.
     * This will get the parent section regardless of nesting because sections are singular.
     *
     * @return The parent section of this group.
     */
    @Override
    public @NotNull ISection getParentSection() {
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
        // Will indent the group in relation to the amount of parent groups there are.
        if (getParentGroup() != null) {
            int x = 0; // Origin
            IGroup parent = getParentGroup();
            while (parent.getParentGroup() != null) {
                parent = parent.getParentGroup();
                x++;
            }
            x += 1; // Consider parent section as 0
            x *= 2; // Double indent
            return getParentSection().indent(x) + getName() + ": ";
        }
        return getParentSection().indent(2) + getName() + ": "; // Parent section has no indentation and this is not a nested group.
    }

    @Override
    public IGroup deserialize(String serializedInput) {
        return getParentSection().getGroup(serializedInput);
    }
}
