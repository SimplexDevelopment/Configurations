package io.github.simplexdev.configurations;

import io.github.simplexdev.configurations.api.IGroup;
import io.github.simplexdev.configurations.api.INode;
import io.github.simplexdev.configurations.api.ISection;
import org.bukkit.plugin.Plugin;

import java.io.*;
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
        if (copyResource) {
            try {
                file.createNewFile();
                InputStream inputStream = plugin.getResource(fileName);
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = inputStream.readAllBytes();
                outputStream.write(buffer);
                outputStream.flush();
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
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
