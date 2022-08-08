package me.filipe.gitemframe.data;

import me.filipe.gitemframe.GItemFrame;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Dados {

    private GItemFrame plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public Dados(GItemFrame plugin) {
        this.plugin = plugin;

        //Guarda e inicia o data.yml
        saveDefaultConfig();
    }

    public void reloadConfig() {
        //Se não existir cria.
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultSream = this.plugin.getResource("data.yml");
        if (defaultSream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultSream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null)
            reloadConfig();

        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null)
            return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "[G-IF] Impossível guardar \"data.yml\" ");
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");

        if (!this.configFile.exists()) {
            this.plugin.saveResource("data.yml", false);
        }
    }

}

