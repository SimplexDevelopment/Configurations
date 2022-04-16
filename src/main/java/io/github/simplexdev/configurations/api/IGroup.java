package io.github.simplexdev.configurations.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public interface IGroup extends Serialize<IGroup> {
    IGroup createNestedGroup(IGroup group, String name, INode<?>... nodes);

    String getName();

    /**
     * Gets the parent group that this group is in.
     * This will return null if there is no parent group associated with this group.
     *
     * @return The parent group this group is associated to. Returns null if there is no parent group.
     */
    @Nullable
    IGroup getParentGroup();

    @Nullable IGroup getNestedGroup(String name);

    boolean hasNest();

    List<IGroup> getNest();

    /**
     * Gets the parent section associated to this group.
     * Groups cannot exist outside a parent section.
     * Groups may be nested.
     *
     * @return The parent section of this group.
     */
    @NotNull
    ISection getParentSection();

    /**
     * @return The nodes contained within this group.
     */
    @Nullable
    Collection<? extends INode<?>> getNodes();

    IGroup addNodes(INode<?>... nodes);

    IGroup removeNodes(INode<?>... nodes);

    IGroup addNode(INode<?> node);

    IGroup removeNode(INode<?> node);
}
