package io.github.simplexdev.configurations.api;

import io.github.simplexdev.configurations.ConfigFactory;
import io.github.simplexdev.configurations.Configurations;
import io.github.simplexdev.configurations.Utils;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("ALL")
public abstract class Yaml extends org.yaml.snakeyaml.Yaml {
    private final Plugin plugin;
    private final File dataFolder;
    private final File configFile;
    private final Utils utils;
    private final ConfigFactory factory;
    private final String fileName;

    private final Map<ISection, ArrayList<IGroup>> groupsInSection = new HashMap<>();
    private final Map<IGroup, ArrayList<INode<?>>> nodesInGroup = new HashMap<>();

    public Yaml(@NotNull Plugin plugin, @Nullable String subDirectory, @NotNull String fileName, @NotNull boolean copyResource) {
        super();
        this.plugin = plugin;
        this.utils = new Utils();
        this.factory = new ConfigFactory();
        this.fileName = fileName;

        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdir();

        if (subDirectory != null) {
            File subDir = new File(dataFolder, subDirectory);
        }

        this.dataFolder = dataFolder;
        File file = new File(dataFolder, fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (copyResource) {
            plugin.saveResource(fileName, true);
        }
        this.configFile = file;
    }

    public Yaml(Plugin plugin, String fileName) {
        this(plugin, null, fileName, false);
    }

    public Yaml(Plugin plugin) {
        this(plugin, null, "config.yml", false);
    }

    public Yaml(Plugin plugin, boolean copyResource) {
        this(plugin, null, "config.yml", true);
    }

    public void load() {
        try {
            super.load(new BufferedReader(new FileReader(configFile)));
        } catch (IOException ex) {
            plugin.getLogger().severe(ex.getMessage());
        }
    }

    public void save(boolean saveResource) {
        if (saveResource) {
            plugin.saveResource(fileName, true);
        }
    }

    @Nullable
    public ISection getSection(String name) {
        for (ISection section : Configurations.availableSections) {
            if (section.getName().equalsIgnoreCase(name)) {
                return section;
            }
        }
        return null;
    }

    public final ConfigFactory getFactory() {
        return factory;
    }
}
