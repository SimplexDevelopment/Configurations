package io.github.simplexdev.configurations.api;

import io.github.simplexdev.configurations.Configurations;
import io.github.simplexdev.configurations.Utils;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public abstract class Yaml {
    private final Plugin plugin;
    private final File dataFolder;
    private final File configFile;
    private final Utils utils;

    private final Map<ISection, ArrayList<IGroup>> groupsPerSection = new HashMap<>();
    private final Map<IGroup, ArrayList<INode<?>>> nodesPerGroup = new HashMap<>();

    public Yaml(Plugin plugin, String fileName, boolean copyResource) {
        this.plugin = plugin;
        this.utils = new Utils();

        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdir();

        this.dataFolder = dataFolder;
        File file = new File(dataFolder, fileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (copyResource) {
            plugin.saveResource(fileName, true);
        }
        this.configFile = file;
    }

    public ISection getSection(String name) {
        for (ISection section : Configurations.availableSections) {
            if (section.getName().equalsIgnoreCase(name)) {
                return section;
            }
        }
        return null;
    }
}
