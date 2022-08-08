package me.filipe.gitemframe;

import me.filipe.gitemframe.commands.ToggleIF;
import me.filipe.gitemframe.data.Dados;
import me.filipe.gitemframe.data.SettingsData;
import me.filipe.gitemframe.events.InvClick;
import me.filipe.gitemframe.events.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class GItemFrame extends JavaPlugin {

    public Dados data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.data = new Dados(this);

        for (Player p : Bukkit.getOnlinePlayers()) {
            SettingsData.load(this, p);
        }

        new InvClick(this);
        new PlayerJoin(this);

        new ToggleIF(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadOnline() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            SettingsData.load(this, p);
        }
    }
}
