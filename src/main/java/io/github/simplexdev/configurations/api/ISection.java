package io.github.simplexdev.configurations.api;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ISection extends Serializable<ISection> {
    @NotNull
    String getName();

    @Nullable
    INode<?> getNode(String name);

    @Contract(pure = true)
    <T> void setNode(String name, T node);

    @Nullable
    IGroup getGroup(String name);
}
