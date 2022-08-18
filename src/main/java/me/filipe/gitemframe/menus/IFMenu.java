package me.filipe.gitemframe.menus;

import me.filipe.gitemframe.data.SettingsData;
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

    public static List<ItemStack> getItems() {
        List<ItemStack> itens = new ArrayList<>();

        itens.add(Utils.createItem(Material.BLACK_STAINED_GLASS_PANE, 1, "", new ArrayList<String>()));

        List<String> lore = new ArrayList<>();
        lore.clear(); lore.add(""); lore.add("&7Clica para &c&l[✗] Desativar&7."); lore.add(""); lore.add("&7Podes ativar usando: &d&l/toggleif"); lore.add("&7&oSe estiveres em SHIFT, também abre este menu."); lore.add("");
        itens.add(Utils.createItem(Material.LIME_DYE, 1, "&a&l[✔] Ativado", lore));

        lore.clear(); lore.add(""); lore.add("&7Clica para &a&l[✔] Ativar&7."); lore.add(""); lore.add("&7Podes ativar usando: &d&l/toggleif"); lore.add("&7&oSe estiveres em SHIFT, também abre este menu."); lore.add("");
        itens.add(Utils.createItem(Material.GRAY_DYE, 1, "&c&l[X] Desativado", lore));

        return itens;
    }

    public static Inventory getInv(ItemFrame i, Boolean b) {
        Inventory inv = Bukkit.createInventory(new IFMenu(), 9*3, "Item Frame");
        ItemStack item = i.getItem();

        ItemStack filler = Utils.createItem(Material.BLACK_STAINED_GLASS_PANE, 1, "", new ArrayList<String>());
        for (int j = 0; j < inv.getSize(); j++) {
            inv.setItem(j, filler);
        }

        inv.setItem(13, item);

        List<String> lore = new ArrayList<>();
        lore.clear(); lore.add(""); lore.add("&7Clica para &c&l[✗] Desativar&7."); lore.add(""); lore.add("&7Podes ativar usando: &d&l/toggleif"); lore.add("&7&oSe estiveres em SHIFT, também abre este menu."); lore.add("");
        ItemStack enable = Utils.createItem(Material.LIME_DYE, 1, "&a&l[✔] Ativado", lore);

        lore.clear(); lore.add(""); lore.add("&7Clica para &a&l[✔] Ativar&7."); lore.add(""); lore.add("&7Podes ativar usando: &d&l/toggleif"); lore.add("&7&oSe estiveres em SHIFT, também abre este menu."); lore.add("");
        ItemStack disable = Utils.createItem(Material.GRAY_DYE, 1, "&c&l[X] Desativado", lore);

        if (b) {
            inv.setItem(inv.getSize() - 1, enable);
        } else {
            inv.setItem(inv.getSize() - 1, disable);
        }

        SettingsData.addL(i.getLocation());

        return inv;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
