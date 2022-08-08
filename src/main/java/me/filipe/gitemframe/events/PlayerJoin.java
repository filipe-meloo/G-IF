package me.filipe.gitemframe.events;

import me.filipe.gitemframe.GItemFrame;
import me.filipe.gitemframe.data.SettingsData;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private GItemFrame plugin;
    public PlayerJoin(GItemFrame plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        SettingsData.load(plugin, e.getPlayer());
    }

}
