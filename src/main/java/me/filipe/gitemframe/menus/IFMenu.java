package me.filipe.gitemframe.menus;

import me.filipe.gitemframe.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class IFMenu implements InventoryHolder {

    private static ItemFrame itemFrame;

    public static Inventory getInv(ItemFrame i) {
        Inventory inv = Bukkit.createInventory(new IFMenu(), InventoryType.DISPENSER, "");
        ItemStack item = i.getItem();

        ItemStack filler = Utils.createItem(Material.BLACK_STAINED_GLASS_PANE, 1, "", new ArrayList<String>());
        for (int j = 0; j < inv.getSize(); j++) {
            inv.setItem(j, filler);
        }

        inv.setItem(4, item);

        List<String> lore = new ArrayList<>();
        lore.clear(); lore.add(""); lore.add("&7Clica para &c&l[✗] Desativar&7."); lore.add(""); lore.add("&7Podes ativar usando: &d&l/toggleif"); lore.add("");
        ItemStack enable = Utils.createItem(Material.LIME_DYE, 1, "&a&l[✔] Ativado", lore);

        inv.setItem(inv.getSize() - 1, enable);

        itemFrame = i;

        return inv;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
