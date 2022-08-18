package me.filipe.gitemframe.data;

import me.filipe.gitemframe.GItemFrame;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SettingsData {

    private static final Map<UUID, Boolean> e = new HashMap<>();
    private static final Map<Location, Boolean> l = new HashMap<>();

    public static Boolean getEnabled(Player p) {
        return e.get(p.getUniqueId());
    }

    public static void toggleEnabled(Player p) {
        Boolean a = e.get(p.getUniqueId());
        e.put(p.getUniqueId(), !a);
    }

    public static Boolean getL(Location loc) {
        return l.get(loc);
    }

    public static void addL(Location loc) {
        l.put(loc, true);
    }

    public static void removeL(Location loc) {
        l.remove(loc);
    }

    public static void load(GItemFrame plugin, Player p) {
        if (e.containsKey(p.getUniqueId())) return;

        if (plugin.data.getConfig().contains("players." + p.getUniqueId())) {
            e.put(p.getUniqueId(), plugin.data.getConfig().getBoolean("players." + p.getUniqueId() + ".enabled"));
        } else {
            e.put(p.getUniqueId(), true);
        }
    }

    public static void saveConfig(GItemFrame plugin) {
        for (Map.Entry<UUID, Boolean> entry : e.entrySet()) {
            plugin.data.getConfig().set("players." + entry.getKey() + ".enabled", entry.getValue());
        }

        plugin.data.saveConfig();
    }

}
