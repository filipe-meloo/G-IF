package me.filipe.gitemframe.events;

import me.filipe.gitemframe.GItemFrame;
import me.filipe.gitemframe.data.SettingsData;
import me.filipe.gitemframe.menus.IFMenu;
import me.filipe.gitemframe.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class InvClick implements Listener {

    private static final List<Location> l = new ArrayList<>();

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

        Player p = (Player) e.getDamager();
        ItemFrame i = (ItemFrame) e.getEntity();

        if (i.getItem().getType() == Material.AIR) return;

        if (l.contains(i.getLocation())) {
            p.sendMessage(Utils.chat("&cEsse menu já está aberto!"));
            e.setCancelled(true);
            return;
        }

        if (!SettingsData.getEnabled(p) && !p.isSneaking()) return;

        l.add(i.getLocation());
        this.itemFrame = i;
        p.openInventory(IFMenu.getInv(i, SettingsData.getEnabled(p)));
        e.setCancelled(true);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof IFMenu)) return;

        e.setCancelled(true);

        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE) && e.getSlot() != 13) return;
        if (e.getClickedInventory() != e.getView().getTopInventory()) return;

        if (e.getSlot() == (e.getView().getTopInventory().getSize() - 1)) {
            SettingsData.toggleEnabled((Player) e.getWhoClicked());
            if (!SettingsData.getEnabled((Player) e.getWhoClicked())) e.getWhoClicked().sendMessage(Utils.chat("&cUsa &l/toggleif &cpara ativar de novo."));
            e.getWhoClicked().closeInventory();
        }

        Player p = (Player) e.getWhoClicked();


        if (e.getSlot() == 13) {

            if (Utils.invFull(p)) {
                p.sendMessage(Utils.chat("&cTens o inventário cheio."));
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                return;
            }

            p.playSound(p, Sound.BLOCK_LEVER_CLICK, 1f, 5f);
            p.getInventory().addItem(e.getView().getItem(13));
            e.getView().setItem(13, null);
        }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if (!(e.getInventory().getHolder() instanceof IFMenu)) return;

        this.itemFrame.setItem(e.getView().getItem(13));
        l.remove(itemFrame.getLocation());
    }

    @EventHandler
    public void onItemFrameBreak(HangingBreakByEntityEvent e) {
        if (e.getEntity() instanceof ItemFrame) {
            ItemFrame i = (ItemFrame) e.getEntity();
            if (l.contains(i.getLocation())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onItemFBreak(HangingBreakEvent e) {
        if (e.getEntity() instanceof ItemFrame) {
            ItemFrame i = (ItemFrame) e.getEntity();
            if (l.contains(i.getLocation())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onItemOut(ProjectileHitEvent e) {
        if (e.getHitEntity() instanceof ItemFrame) {
            ItemFrame i = (ItemFrame) e.getHitEntity();
            if (l.contains(i.getLocation())) {
                e.setCancelled(true);
            }
        }
    }

}
