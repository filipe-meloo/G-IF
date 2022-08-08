package me.filipe.gitemframe.commands;

import me.filipe.gitemframe.GItemFrame;
import me.filipe.gitemframe.data.SettingsData;
import me.filipe.gitemframe.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleIF implements CommandExecutor {

    private GItemFrame plugin;
    public ToggleIF(GItemFrame plugin) {
        this.plugin = plugin;
        plugin.getCommand("toggleif").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("toggleif")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Apenas players");
                return true;
            }

            SettingsData.toggleEnabled((Player) sender);
            if (SettingsData.getEnabled((Player) sender)) {
                sender.sendMessage(Utils.chat("&aAtivaste o menu custom das item frames!"));
            } else {
                sender.sendMessage(Utils.chat("&cDesativaste o menu custom das item frames!"));
            }

            return true;
        }

        return false;
    }
}
