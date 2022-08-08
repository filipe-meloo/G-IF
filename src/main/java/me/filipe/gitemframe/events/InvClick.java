package me.filipe.gitemframe.events;

import me.filipe.gitemframe.GItemFrame;
import me.filipe.gitemframe.data.SettingsData;
import me.filipe.gitemframe.menus.IFMenu;
import me.filipe.gitemframe.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InvClick implements Listener {

    private ItemFrame itemFrame;
    private GItemFrame plugin;
    public InvClick(GItemFrame plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void LeftClickIF(EntityDamageByEntityEvent e) {

        if (!(e.getEntity() instanceof ItemFrame)) return;
        if (!(e.getDamager() instanceof Player)) return;
        if ((((ItemFrame) e.getEntity()).getItem().getType() == Material.AIR)) return;

        Player p = (Player) e.getDamager();

        if (!SettingsData.getEnabled(p)) return;

        ItemFrame i = (ItemFrame) e.getEntity();

        this.itemFrame = i;
        p.openInventory(IFMenu.getInv(i));
        e.setCancelled(true);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof IFMenu)) return;
        if (e.getCurrentItem() == null) return;
        if (e.getClickedInventory() == e.getView().getBottomInventory()) return;

        if (e.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
            e.setCancelled(true);
            return;
        }

        if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).contains("Ativado") && e.getCurrentItem().getType().equals(Material.LIME_DYE)) {
            e.setCancelled(true);
            SettingsData.toggleEnabled((Player) e.getWhoClicked());
            e.getWhoClicked().sendMessage(Utils.chat("&cUsa &l/toggleif &cpara ativar de novo."));
            e.getWhoClicked().closeInventory();
        }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if (!(e.getInventory().getHolder() instanceof IFMenu)) return;

        if (e.getInventory().getItem(4) == null) {
            this.itemFrame.setItem(new ItemStack(Material.AIR));
        }

    }

}
