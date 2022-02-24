package io.github.simplexdev.configurations.api;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface IGroup extends Serializable<IGroup>{
    IGroup createGroup(String name, INode<?>... nodes);

    String getName();

    /**
     * Gets the parent group that this group is in.
     * This will return null if there is no parent group associated with this group.
     * @param name
     * @return The parent group this group is associated to. Returns null if there is no parent group.
     */
    @Nullable
    IGroup getGroup(String name);

    /**
     * Gets the parent section associated to this group.
     * Groups cannot exist outside a parent section.
     * Groups may be nested.
     * @return The parent section of this group.
     */
    @NotNull
    ISection getSection();

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
