package com.seailz.seailzcore.core.utils;

import com.seailz.seailzcore.SeailzCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigUtils {

    private final String name;

    public ConfigUtils(String fileName) {
        this.name = fileName;
    }

    public FileConfiguration getConfig() {
        File file = new File(SeailzCore.getInstance().getDataFolder(), this.name + ".yml");
        return YamlConfiguration.loadConfiguration(file);
    }
}
