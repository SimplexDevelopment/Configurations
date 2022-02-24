package io.github.simplexdev.configurations;

import io.github.simplexdev.configurations.api.ISection;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class Configurations {
    public static final ConfigFactory factory = new ConfigFactory();
    public static final List<ISection> availableSections = new ArrayList<>();

    private final String pluginName;
    private final Plugin plugin;

    public Configurations(@NotNull Plugin plugin) {
        this.plugin = plugin;
        this.pluginName = plugin.getName();
    }

    public String getPluginName() {
        return pluginName;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
